package com.portal.auth.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.portal.auth.shiro.ButtonAlgorithm;
import com.portal.auth.shiro.db.service.ShiroDataCacheAwareService;
import com.portal.common.page.JsonData;
import com.portal.common.page.JsonResponse;
import com.portal.auth.controller.form.IS_OK;
import com.portal.auth.controller.form.TreeItem;
import com.portal.auth.authdb.entity.Apps;
import com.portal.auth.authdb.entity.Func;
import com.portal.auth.authdb.entity.Operbutton;
import com.portal.auth.authdb.service.IAppsService;
import com.portal.auth.authdb.service.IFuncService;
import com.portal.auth.authdb.service.IOperbuttonService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by liuquan on 2016/12/19
 */
@Controller
@RequestMapping("/func")
public class FuncController {


    public static final String ROOT = "ROOT";
    @Autowired
    private IFuncService funcService;


    @Autowired
    private CacheManager shiroRedisCacheManager;

    @Autowired
    private IAppsService appsService;

    @Autowired
    private IOperbuttonService operbuttonService;
    /**
     * http://localhost:4444/func/treePage
     *
     * @return current
     * size
     */
    @RequiresPermissions("auth_func:1")
    @RequestMapping(value = "/treePage", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("isOkSelectList", IS_OK.values());
        model.addAttribute("isOkSelectList", IS_OK.values());

        Apps p1 = new Apps();
        List<Apps> appses = appsService.selectList(new EntityWrapper<Apps>(p1));
        if (CollectionUtils.isEmpty(appses)) {
            appses = new ArrayList<Apps>();
        }
        model.addAttribute("appsesSelectList", appses);
        return "/page/auth/func/func_treePage";
    }

    @RequiresPermissions("auth_func:1")
    @ResponseBody
    @RequestMapping(value = "/rootsJson", method = RequestMethod.GET)
    public JsonData<?> rootsJson() {
        final TreeItem treeItem = new TreeItem();
        treeItem.setText("点我添加根节点");
        treeItem.setChildren(true);
        treeItem.setType("default");
        treeItem.setId(ROOT);
        return new JsonData<Object>(new ArrayList<TreeItem>(){{
            add(treeItem);
        }});
    }

    @RequiresPermissions("auth_func:1")
    @ResponseBody
    @RequestMapping(value = "/childrenJson", method = RequestMethod.GET)
    public JsonData<?> childrenJson(@RequestParam("id") String parentId ) {
        List<Func> funcs;
//        if (parentId.equals(ROOT)) {
//            Condition condition = new Condition();
//            condition.isNull(Func.PARID);
//            condition.or(Func.PARID +"= \"\"");
//            funcs = funcService.selectList(condition);
//        }else{
            funcs = queryByParentId(parentId);
//        }

        ArrayList<TreeItem> itemList = new ArrayList<TreeItem>();
        if (CollectionUtils.isNotEmpty(funcs)) {
            for (Func temp : funcs) {
                List<Func> childreds = queryByParentId(temp.getId());
                itemList.add(new TreeItem(temp,CollectionUtils.isNotEmpty(childreds)));
            }
        }

        return new JsonData<Object>(itemList);
/*        return new JsonData<Object>("[\n" +
                "   {\n" +
                "      \"text\" : \"New node\",\n" +
                "      \"children\" : true,\n" +
                "      \"type\" : \"default\",\n" +
                "      \"id\" : \""+p1+"\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"text\" : \"New node 2\",\n" +
                "      \"children\" : false,\n" +
                "      \"id\" : \""+p2+"\",\n" +
                "      \"icon\" : \"fa fa-file-code-o\"\n" +
                "   }\n" +
                "]");*/
    }

    private List<Func> queryByParentId(@RequestParam("id") String parentId) {
        List<Func> funcs;
        Func p1 = new Func();
        p1.setParid(parentId);
        EntityWrapper<Func> wrapper = new EntityWrapper<Func>(p1);
        wrapper.orderBy(Func.ORDERNUM, true);
        funcs = funcService.selectList(wrapper);
        return funcs;
    }
//    @ResponseBody
//    @RequestMapping(value = "/childrenJson", method = RequestMethod.GET)
//    public JsonData<?> childrenJson(String id ) {
//
//
//        int funcCount = funcService.selectCount(new EntityWrapper<Func>(new Func()));
//        if (funcCount == 0) {
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("text","");
//        }
//        String p1="p1";
//        String p2 = "p2";
//        if (id != null && !id.equals("#")) {
//            p1 = UUID.randomUUID().toString();
//            p2 = UUID.randomUUID().toString();
//        }
//
//
//
//        return new JsonData<Object>("[\n" +
//                "   {\n" +
//                "      \"text\" : \"New node\",\n" +
//                "      \"children\" : true,\n" +
//                "      \"type\" : \"default\",\n" +
//                "      \"id\" : \""+p1+"\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"text\" : \"New node 2\",\n" +
//                "      \"children\" : false,\n" +
//                "      \"id\" : \""+p2+"\",\n" +
//                "      \"icon\" : \"fa fa-file-code-o\"\n" +
//                "   }\n" +
//                "]");
//    }

    @ResponseBody
    @RequestMapping(value = "/getItemInfoById", method = RequestMethod.GET)
    public JsonData<?> getItemInfoById(String id ) {
        if (StringUtils.isBlank(id) || id.equals(ROOT)) {
            Func func = new Func();
            func.setName("无");
            return new JsonData<Object>(JsonResponse.buildSuccess(func));
        }
        Func func = funcService.selectById(id);
        return new JsonData<Object>(JsonResponse.buildSuccess(func));
    }
//    @ResponseBody
//    @RequestMapping(value = "/pageJson", method = RequestMethod.GET)
//    public JsonData<BootstrapTablePage> pageJson(BootstrapTablePageRequest<AppSso> pageRequest, AppSso queryParam) {
//        Condition conditon = buildCondition(queryParam);
//
//        Page<AppSso> selectPage = appSsoService.selectPage(pageRequest.toPage(), conditon);
//
//        List<AppSsoForm> result = new ArrayList<AppSsoForm>();
//        List<AppSso> records = selectPage.getRecords();
//        if (CollectionUtils.isNotEmpty(records)) {
//            for (AppSso temp : records) {
//                Apps apps = appsService.selectById(temp.getAppid());
//                result.add(AppSsoForm.build(apps, temp));
//            }
//        }
//
//
//        return new JsonData<BootstrapTablePage>(new BootstrapTablePage(selectPage,result));
//    }

//    private Condition buildCondition(AppSso queryParam) {
//        Condition conditon = new Condition();
//        if (StringUtils.isNotBlank(queryParam.getHost())) {
//            conditon.like(AppSso.HOST, queryParam.getHost());
//        }
//        if (queryParam.getAppid() != null) {
//            conditon.eq(AppSso.APPID, queryParam.getAppid());
//        }
//        return conditon;
//    }
//
//    @RequestMapping(value = "/savePage", method = RequestMethod.GET)
//    public String add(AppSso appSso, Model model) {
//        List<Apps> appses = buildSelectItem();
//
//
//        model.addAttribute("apps", appses);
//        if (appSso.getId()!=null) {
//            appSso = appSsoService.selectById(appSso.getId());
//        }
//        model.addAttribute("vo", appSso);
//        return "/page/auth/appsso/appsso_add";
//    }
//
//    private List<Apps> buildSelectItem() {
//        List<Apps> appses = appsService.selectList(new EntityWrapper<Apps>(new Apps()));
//        if (CollectionUtils.isNotEmpty(appses)) {
//            for (Apps temp : appses) {
//                temp.setAppnamecn(temp.getAppname()+"("+temp.getAppid()+")");
//            }
//        }
//        return appses;
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/save", method = {RequestMethod.POST})
//    public JsonData<Object> save(AppSso appSso) {
//
//        generateObj(appSso);
//        appSsoService.insertOrUpdate(appSso);
//        return new JsonData<Object>(JsonResponse.buildSuccess(appSso));
//    }
//
//    private void generateObj(AppSso appSso) {
//        if (appSso.getId() != null) {
//            return ;
//        }
//        AppSso condition = new AppSso();
//        condition.setAppid(appSso.getAppid());
//        List<AppSso> existNowAppSso = appSsoService.selectList(new EntityWrapper<AppSso>(condition));
//        if (CollectionUtils.isNotEmpty(existNowAppSso)) {
//            appSso.setKeyvalue(existNowAppSso.get(0).getKeyvalue());
//            appSso.setKeysign(existNowAppSso.get(0).getKeysign());
//            appSso.setKeyid(existNowAppSso.get(0).getKeyid());
//        }else{
//            appSso.setKeyid(appSso.getAppid());
//            appSso.setKeyvalue(UUID.randomUUID().toString().substring(0,6));
//            appSso.setKeysign(UUID.randomUUID().toString().substring(24,30));
//        }
//
//        Apps app = appsService.selectById(appSso.getAppid());
//        appSso.setAppname(app.getAppname());
//        appSso.setType(app.getApptype());
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
//    public JsonData<Object> delete(@RequestParam("ids[]") String[] ids) {
//        appSsoService.deleteBatchIds(Arrays.asList(ids));
//        return new JsonData<Object>(JsonResponse.buildSuccess());
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/alreadyAuth", method = {RequestMethod.GET})
//    public JsonData<List<AppSso>> delete(Integer appId) {
//        AppSso queryCondition = new AppSso();
//        queryCondition.setAppid(appId);
//
//        List<AppSso> appSsos = appSsoService.selectList(new EntityWrapper<AppSso>(queryCondition));
//        return new JsonData<List<AppSso>>(appSsos);
//    }


    @ResponseBody
    @RequestMapping(value = "/queryButtons", method = {RequestMethod.GET})
    public JsonData<List<Operbutton>> delete(@RequestParam(value = "id",required = false) String func) {

        if (StringUtils.isBlank(func)) {
            return new JsonData<List<Operbutton>>(new ArrayList<Operbutton>());
        }


        Operbutton queryCondition = new Operbutton();
        queryCondition.setFunc(func);
        EntityWrapper<Operbutton> wrapper = new EntityWrapper<Operbutton>(queryCondition);
        wrapper.orderBy(Operbutton.BUTTON_CODE, true);
        List<Operbutton> operbuttons = operbuttonService.selectList(wrapper);
        return new JsonData<List<Operbutton>>(operbuttons==null?new ArrayList<Operbutton>():operbuttons);
    }


    @RequiresPermissions("auth_func:2")
    @ResponseBody
    @RequestMapping(value = "/saveFuncAjax", method = {RequestMethod.POST})
    public JsonData<JsonResponse> saveFuncAjax(Func func) {
        boolean isAdd=false;
        if (StringUtils.isBlank(func.getId())) {
            isAdd=true;
        }

        funcService.insertOrUpdate(func);

        if (isAdd) {
            final Operbutton viewAuth = new Operbutton();
            viewAuth.setFunc(func.getId());
            viewAuth.setButtonCode(1);
            viewAuth.setName("查看");
            viewAuth.setIsok(String.valueOf(IS_OK.OK.getCode()));

            final Operbutton addAuth = new Operbutton();
            addAuth.setFunc(func.getId());
            addAuth.setButtonCode(2);
            addAuth.setName("新增");
            addAuth.setIsok(String.valueOf(IS_OK.OK.getCode()));

            final Operbutton updateAuth = new Operbutton();
            updateAuth.setFunc(func.getId());
            updateAuth.setButtonCode(4);
            updateAuth.setName("修改");
            updateAuth.setIsok(String.valueOf(IS_OK.OK.getCode()));

            final Operbutton deleteAuth = new Operbutton();
            deleteAuth.setFunc(func.getId());
            deleteAuth.setButtonCode(8);
            deleteAuth.setName("删除");
            deleteAuth.setIsok(String.valueOf(IS_OK.OK.getCode()));


            operbuttonService.insertBatch(new ArrayList<Operbutton>() {{
                add(viewAuth);
                add(addAuth);
                add(updateAuth);
                add(deleteAuth);
            }});
        }

        shiroRedisCacheManager.getCache(ShiroDataCacheAwareService.SHIRO_AUTHZATION_INFO_REDIS_CACHE_NAME ).clear();
        return new JsonData<JsonResponse>(JsonResponse.buildSuccess(""));
    }

    @RequiresPermissions("auth_func:2")
    @ResponseBody
    @RequestMapping(value = "/deleteFuncByIdAjax", method = {RequestMethod.POST})
    public JsonData<JsonResponse> deleteFuncByIdAjax(String id) {
        Set<String> ids = buildDeleteIds(id);
        funcService.deleteBatchIds(new ArrayList<Serializable>(ids));
        shiroRedisCacheManager.getCache(ShiroDataCacheAwareService.SHIRO_AUTHZATION_INFO_REDIS_CACHE_NAME ).clear();
        return new JsonData<JsonResponse>(JsonResponse.buildSuccess(""));
    }

    public Set<String> buildDeleteIds(String funcId){
        Set<String> needDeleteIds = new HashSet<String>();
        needDeleteIds.add(funcId);
        Func func = new Func();
        func.setParid(funcId);
        List<Func> funcs = funcService.selectList(new EntityWrapper<Func>(func));
        if (CollectionUtils.isNotEmpty(funcs)) {
            for (Func temp : funcs) {
                needDeleteIds.addAll(buildDeleteIds(temp.getId()));
            }
        }
        return needDeleteIds;
    }


    @RequiresPermissions("auth_func:2")
    @ResponseBody
    @RequestMapping(value = "/saveOpperBtnAjax", method = {RequestMethod.POST})
        public JsonData<JsonResponse> saveOpperBtnAjax(Operbutton operbutton) {


        if (StringUtils.isBlank(operbutton.getId())) {
            Integer buttonCode = operbutton.getButtonCode();
            Operbutton p1 = new Operbutton();
            p1.setButtonCode(buttonCode);
            p1.setFunc(operbutton.getFunc());
            List<Operbutton> butonAuthCodeAlreadyExist = operbuttonService.selectList(new EntityWrapper<Operbutton>(p1));
            if (CollectionUtils.isNotEmpty(butonAuthCodeAlreadyExist)) {
                return new JsonData<JsonResponse>(JsonResponse.buildError("授权码已存在，不得重复，请更换"));
            }
        }

        if (!ButtonAlgorithm.isValidSeed(operbutton.getButtonCode())) {
            return new JsonData<JsonResponse>(JsonResponse.buildError("授权码非法，必需为2^n"));
        }



        operbuttonService.insertOrUpdate(operbutton);
        //System.out.println(JSON.toJSON(operbutton));


        shiroRedisCacheManager.getCache(ShiroDataCacheAwareService.SHIRO_AUTHZATION_INFO_REDIS_CACHE_NAME ).clear();
        return new JsonData<JsonResponse>(JsonResponse.buildSuccess(""));
    }

    @RequiresPermissions("auth_func:2")
    @ResponseBody
    @RequestMapping(value = "/deleteOpperBtnByIdAjax", method = {RequestMethod.POST})
    public JsonData<JsonResponse> deleteOpperBtnByIdAjax(String  id) {
        operbuttonService.deleteById(id);

        shiroRedisCacheManager.getCache(ShiroDataCacheAwareService.SHIRO_AUTHZATION_INFO_REDIS_CACHE_NAME ).clear();
        return new JsonData<JsonResponse>(JsonResponse.buildSuccess(""));
    }

    @ResponseBody
    @RequestMapping(value = "/nextBtnAuth", method = {RequestMethod.GET,RequestMethod.POST})
    public JsonData<JsonResponse> nextBtnAuth(String  func) {
        Long nextBtnAuth = operbuttonService.getNextBtnAuth(func);
        return new JsonData<JsonResponse>(JsonResponse.buildSuccess(nextBtnAuth));
    }





    @RequestMapping(value = "/selectIconPage", method = RequestMethod.GET)
    public String selectIconPage(Model model) {

        return "/page/auth/func/selectIconPage";
    }

}
