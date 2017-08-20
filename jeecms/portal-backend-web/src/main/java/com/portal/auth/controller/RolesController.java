package com.portal.auth.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.portal.auth.shiro.ButtonAlgorithm;
import com.portal.auth.shiro.db.service.ShiroDataCacheAwareService;
import com.portal.common.page.BootstrapTablePage;
import com.portal.common.page.BootstrapTablePageRequest;
import com.portal.common.page.JsonData;
import com.portal.common.page.JsonResponse;
import com.portal.auth.controller.form.IS_OK;
import com.portal.auth.controller.form.SaveRoleFunAjaxForm;
import com.portal.auth.controller.form.TreeItem;
import com.portal.auth.controller.form.TreeItemState;
import com.portal.auth.authdb.entity.Func;
import com.portal.auth.authdb.entity.Operbutton;
import com.portal.auth.authdb.entity.RoleFunc;
import com.portal.auth.authdb.entity.Roles;
import com.portal.auth.authdb.service.IFuncService;
import com.portal.auth.authdb.service.IOperbuttonService;
import com.portal.auth.authdb.service.IRoleFuncService;
import com.portal.auth.authdb.service.IRolesService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created by liuquan on 2016/12/19
 */
@Controller
@RequestMapping("/roles")
public class RolesController {

    @Autowired
    private CacheManager shiroRedisCacheManager;

    @Autowired
    private IRolesService rolesService;

    
    @Autowired
    private IRoleFuncService roleFuncService;

    @Autowired
    private IFuncService funcService;


    @Autowired
    private IOperbuttonService operbuttonService;
    /**
     * http://localhost:4444/roles/listPage
     *
     * @return current
     * size
     */
    @RequiresPermissions("auth_role:1")
    @RequestMapping(value = "/listPage", method = RequestMethod.GET)
    public String list() {
        return "/page/auth/roles/roles_list";
    }

    @RequiresPermissions("auth_role:1")
    @ResponseBody
    @RequestMapping(value = "/pageJson", method = RequestMethod.GET)
    public JsonData<BootstrapTablePage> pageJson(BootstrapTablePageRequest<Roles> pageRequest, Roles queryParam) {
        Condition conditon = buildCondition(queryParam);
        Page<Roles> selectPage = rolesService.selectPage(pageRequest.toPage(), conditon);
        return new JsonData<BootstrapTablePage>(new BootstrapTablePage(selectPage));
    }

    private Condition buildCondition(Roles queryParam) {
        Condition conditon = new Condition();
        if (StringUtils.isNotBlank(queryParam.getName())) {
            conditon.like(Roles.NAME, queryParam.getName());
        }
        if (StringUtils.isNotBlank(queryParam.getIsok())) {
            conditon.eq(Roles.ISOK, queryParam.getIsok());
        }
        return conditon;
    }


    @RequiresPermissions("auth_role:2")
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addPage(Roles roles, Model model) {
        return savePage(roles, model);
    }
    @RequiresPermissions("auth_role:4")
    @RequestMapping(value = "/editPage", method = RequestMethod.GET)
    public String editPage(Roles roles, Model model) {
        return savePage(roles, model);
    }

    public String savePage(Roles roles, Model model) {
        if (roles.getId()!=null) {
            roles = rolesService.selectById(roles.getId());
        }
        model.addAttribute("isOkSelect", IS_OK.values());
        model.addAttribute("vo", roles);
        return "/page/auth/roles/roles_save";
    }



    @RequiresPermissions("auth_role:2")
    @ResponseBody
    @RequestMapping(value = "/addAjax", method = {RequestMethod.POST})
    public JsonData<Object> addAjax(Roles role) {
        return saveAjax(role);
    }

    @RequiresPermissions("auth_role:4")
    @ResponseBody
    @RequestMapping(value = "/editAjax", method = {RequestMethod.POST})
    public JsonData<Object> editAjax(Roles role) {
        return saveAjax(role);
    }


    public JsonData<Object> saveAjax(Roles role) {
        if (StringUtils.isBlank(role.getId())) {
            Roles queryParam = new Roles();
            queryParam.setName(role.getName());
            Roles alreadyExist = rolesService.selectOne(new EntityWrapper<Roles>(queryParam));
            if (alreadyExist != null) {
              return   new JsonData<Object>(JsonResponse.buildError("失败，该角色已存在"));
            }
        }

        rolesService.insertOrUpdate(role);
        shiroRedisCacheManager.getCache(ShiroDataCacheAwareService.SHIRO_AUTHZATION_INFO_REDIS_CACHE_NAME ).clear();
        return new JsonData<Object>(JsonResponse.buildSuccess(role));
    }


    @RequiresPermissions("auth_role:8")
    @ResponseBody
    @RequestMapping(value = "/deleteAjax", method = {RequestMethod.POST})
    public JsonData<Object> delete(@RequestParam("ids[]") String[] ids) {
        rolesService.deleteBatchIds(Arrays.asList(ids));
        shiroRedisCacheManager.getCache(ShiroDataCacheAwareService.SHIRO_AUTHZATION_INFO_REDIS_CACHE_NAME ).clear();
        return new JsonData<Object>(JsonResponse.buildSuccess());
    }


    @RequiresPermissions("auth_role:16")
    @RequestMapping(value = "/selectFuncTreePage", method = RequestMethod.GET)
    public String selectFuncTreePage(Roles roles, Model model,String roleId) {
        model.addAttribute("roleId",roleId);


        if (roles.getId()!=null) {
            roles = rolesService.selectById(roles.getId());
        }

        model.addAttribute("isOkSelect", IS_OK.values());

        model.addAttribute("vo", roles);
        return "/page/auth/roles/roles_selectFuncTreePage";
    }



//    public JsonData<?> childrenJson(String id ) {
//
//        return new JsonData<Object>("[\n" +
//                "            {\n" +
//                "                \"id\":999,\n" +
//                "                \"text\": \"根菜单999\",\n" +
//                "                \"children\": [\n" +
//                "                    {\"id\":1,\"text\": \"菜单1\", \"state\": {\"selected\": true}},\n" +
//                "                    {\"id\":2,\"text\": \"菜单2\", \"icon\": \"//jstree.com/tree-icon.png\"},\n" +
//                "                    {\"id\":3,\"text\": \"菜单3\", \"state\": {\"opened\": true}, \"children\": [\n" +
//                "                        {\"id\":\"3_1\",\"text\": \"三级1\", \"icon\": \"//jstree.com/tree-icon.png\"},\n" +
//                "                        {\"id\":\"3_2\",\"text\": \"三级2\", \"icon\": \"//jstree.com/tree-icon.png\"}\n" +
//                "                    ]},\n" +
//                "                    {\"id\":4,\"text\": \"菜单4\", \"icon\": \"glyphicon glyphicon-leaf\"}\n" +
//                "                ]\n" +
//                "            },\n" +
//                "            \"And wholerow selection\"\n" +
//                "        ]");
//    }
    @RequiresPermissions("auth_role:16")
    @ResponseBody
    @RequestMapping(value = "/childrenJson", method = RequestMethod.GET)
    public JsonData<?> childrenJson( String roleId ,Model model) {
        Set<String> funcIdSet = new HashSet<String>();
        RoleFunc p1 = new RoleFunc();
        p1.setRoleid(roleId);
        List<RoleFunc> roleFuncs = roleFuncService.selectList(new EntityWrapper<RoleFunc>(p1));
        if (CollectionUtils.isNotEmpty(roleFuncs)) {
            for (RoleFunc temp : roleFuncs) {
                funcIdSet.add(temp.getFuncid());
            }
        }




        List<TreeItem> treeItemsList = pid2TreeItemList(FuncController.ROOT,Collections.unmodifiableSet(funcIdSet),roleId);

        final TreeItem rootTree = new TreeItem();
        rootTree.setText("目录");
        rootTree.setChildren(treeItemsList);
        rootTree.setType("default");
        rootTree.setId(FuncController.ROOT);
        TreeItemState treeItemState = new TreeItemState();
        treeItemState.setOpened(true);
        treeItemState.setSelected(false);
        rootTree.setState(treeItemState);
        return new JsonData<Object>(new ArrayList<TreeItem>(){{
            add(rootTree);
        }});
    }
    private List<TreeItem> pid2TreeItemList(String pid, Set<String> funcIdSet,String roleId){
        Func p2 = new Func();
        p2.setParid(pid);
        List<Func> funcList = funcService.selectList(new EntityWrapper<Func>(p2));
        ArrayList<TreeItem> itemList = new ArrayList<TreeItem>();
        if (CollectionUtils.isNotEmpty(funcList)) {
            for (Func temp : funcList) {

                //button
                ArrayList<TreeItem> buttonList = getTreeButtons(temp,roleId);

                List<TreeItem> subMenu = pid2TreeItemList(temp.getId(), funcIdSet,roleId);
                subMenu.addAll(buttonList);
                //subMenu,funcIdSet.contains(temp.getId())
                itemList.add(new TreeItem(temp,subMenu,false ,true));
            }
        }



        return itemList;
    }

    private ArrayList<TreeItem> getTreeButtons(Func temp,String roleId) {
        RoleFunc p8 = new RoleFunc();
        p8.setRoleid(roleId);
        p8.setFuncid(temp.getId());
        RoleFunc roleFunc = roleFuncService.selectOne(new EntityWrapper<RoleFunc>(p8));

        ArrayList<TreeItem> buttonList = new ArrayList<TreeItem>();
        Operbutton p3 = new Operbutton();
        p3.setFunc(temp.getId());
        List<Operbutton> operbuttons = operbuttonService.selectList(new EntityWrapper<Operbutton>(p3));
        if (CollectionUtils.isNotEmpty(operbuttons)) {
            for (Operbutton tempBtn : operbuttons) {
                boolean select = false;
                if (roleFunc == null) {
                    select=false;
                }else{
                    select = ButtonAlgorithm.contain(roleFunc.getButtonauth(), tempBtn.getButtonCode());
                }


                buttonList.add(new TreeItem(tempBtn, select));
            }
        }
        return buttonList;
    }


    @RequiresPermissions("auth_role:16")
    @ResponseBody
    @RequestMapping(value = "/saveRoleFuncAjax", method = RequestMethod.POST)
    public JsonData<?> saveRoleFuncAjax(SaveRoleFunAjaxForm form) {
        System.out.println(JSON.toJSON(form));

        RoleFunc p1 = new RoleFunc();
        p1.setRoleid(form.getRoleId());
        roleFuncService.delete(new EntityWrapper<RoleFunc>(p1));

        if (form.getRoleFunc() != null && form.getRoleFunc().length >= 0) {
            List<RoleFunc> entityList = Arrays.asList(form.getRoleFunc());

            roleFuncService.insertBatch(entityList);
        }
        shiroRedisCacheManager.getCache(ShiroDataCacheAwareService.SHIRO_AUTHZATION_INFO_REDIS_CACHE_NAME ).clear();

        return new JsonData<Object>(JsonResponse.buildSuccess());
    }
}
