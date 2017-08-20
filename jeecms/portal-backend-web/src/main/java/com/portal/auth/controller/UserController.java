
package com.portal.auth.controller;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.portal.auth.shiro.db.service.ShiroDataCacheAwareService;
import com.portal.common.page.BootstrapTablePage;
import com.portal.common.page.BootstrapTablePageRequest;
import com.portal.common.page.JsonData;
import com.portal.common.page.JsonResponse;
import com.portal.auth.controller.form.IS_OK;
import com.portal.auth.controller.form.RoleUserSelectFormItem;
import com.portal.auth.controller.form.SEX;
import com.portal.auth.authdb.entity.RoleUser;
import com.portal.auth.authdb.entity.Roles;
import com.portal.auth.authdb.entity.Users;
import com.portal.auth.authdb.service.IRoleUserService;
import com.portal.auth.authdb.service.IRolesService;
import com.portal.auth.authdb.service.IUsersService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.DefaultPasswordService;
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
@RequestMapping("/user")
public class UserController {


    @Autowired
    private CacheManager shiroRedisCacheManager;

    @Autowired
    private IUsersService usersService;

    @Autowired
    private IRoleUserService roleUserService;

    @Autowired
    private IRolesService rolesService;


    @Autowired
    private DefaultPasswordService defaultPasswordService;
    /**
     * http://localhost:4444/user/listPage
     *
     * @return current
     * size
     */
    @RequiresPermissions("auth_user:1")
    @RequestMapping(value = "/listPage", method = RequestMethod.GET)
    public String list() {
        return "/page/auth/user/user_list";
    }

    @RequiresPermissions("auth_user:1")
    @ResponseBody
    @RequestMapping(value = "/pageJson", method = RequestMethod.GET)
    public JsonData<BootstrapTablePage> pageJson(BootstrapTablePageRequest<Users> pageRequest, Users queryParam) {
        Condition conditon = buildCondition(queryParam);
        Page<Users> selectPage = usersService.selectPage(pageRequest.toPage(), conditon);
        return new JsonData<BootstrapTablePage>(new BootstrapTablePage(selectPage));
    }

    private Condition buildCondition(Users queryParam) {
        Condition conditon = new Condition();
        if (StringUtils.isNotBlank(queryParam.getLoginname())) {
            conditon.like(Users.LOGINNAME, queryParam.getLoginname());
        }

        if (StringUtils.isNotBlank(queryParam.getName())) {
            conditon.like(Users.NAME, queryParam.getName());
        }

        if (StringUtils.isNotBlank(queryParam.getMobilephone())) {
            conditon.like(Users.MOBILEPHONE, queryParam.getMobilephone());
        }

        if (StringUtils.isNotBlank(queryParam.getEmail())) {
            conditon.like(Users.EMAIL, queryParam.getEmail());
        }

        if (StringUtils.isNotBlank(queryParam.getIsok())) {
            conditon.eq(Roles.ISOK, queryParam.getIsok());
        }
        return conditon;
    }


    @RequiresPermissions("auth_user:2")
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String add(Users user, Model model) {
        return save(user, model);
    }

    @RequiresPermissions("auth_user:4")
    @RequestMapping(value = "/editPage", method = RequestMethod.GET)
    public String edit(Users user, Model model) {
        return save(user, model);
    }

    public String save(Users user, Model model) {
        if (user.getId()!=null) {
            user = usersService.selectById(user.getId());
        }

        model.addAttribute("isOkSelect", IS_OK.values());
        model.addAttribute("sexSelect", SEX.values());
        model.addAttribute("vo", user);
        return "/page/auth/user/user_save";
    }


    @RequiresPermissions("auth_user:2")
    @ResponseBody
    @RequestMapping(value = "/addAjax", method = {RequestMethod.POST})
    public JsonData<Object> addAjax(Users user) {
        return save(user);
    }


    @RequiresPermissions("auth_user:4")
    @ResponseBody
    @RequestMapping(value = "/editAjax", method = {RequestMethod.POST})
    public JsonData<Object> editAjax(Users user) {
        return save(user);
    }


    public JsonData<Object> save(Users user) {
        if (StringUtils.isBlank(user.getId())) {
            user.setAddtime(new Date());
            Users queryParam = new Users();
            queryParam.setLoginname(user.getLoginname());
            Users alreadyExist = usersService.selectOne(new EntityWrapper<Users>(queryParam));
            if (alreadyExist != null) {
                return   new JsonData<Object>(JsonResponse.buildError(String.format("失败，该登录用户名[%s]已存在",user.getLoginname())));
            }

            /**
             * 加密密码
             */
            user.setPwd(defaultPasswordService.encryptPassword(user.getPwd()));
        }
        usersService.insertOrUpdate(user);


        return new JsonData<Object>(JsonResponse.buildSuccess(user));
    }


    @RequiresPermissions("auth_user:8")
    @ResponseBody
    @RequestMapping(value = "/deleteAjax", method = {RequestMethod.POST})
    public JsonData<Object> delete(@RequestParam("ids[]") String[] ids) {
        usersService.deleteBatchIds(Arrays.asList(ids));
        return new JsonData<Object>(JsonResponse.buildSuccess());
    }


    @RequiresPermissions("auth_user:16")
    @RequestMapping(value = "/configRolePage", method = {RequestMethod.GET})
    public String configRolePage(String id,Model model){

        RoleUser p1 = new RoleUser();
        p1.setUsers(id);
        List<RoleUser> roleUsers = roleUserService.selectList(new EntityWrapper<RoleUser>(p1));


        Map<String, RoleUser> roleIdUserMap = new HashMap<String, RoleUser>();
        if (CollectionUtils.isNotEmpty(roleUsers)) {
            for (RoleUser temp : roleUsers) {
                roleIdUserMap.put(temp.getRoles(), temp);
            }
        }


        Roles p2 = new Roles();
        p2.setIsok(String.valueOf(IS_OK.OK.getCode()));
        List<Roles> roles = rolesService.selectList(new EntityWrapper<Roles>(p2));


        List<RoleUserSelectFormItem> selectRolesList = new ArrayList<RoleUserSelectFormItem>();
        if (CollectionUtils.isNotEmpty(roles)) {
            for (Roles temp : roles) {
                String roleId = temp.getId();
                RoleUser roleUser = roleIdUserMap.get(roleId);
                selectRolesList.add(new RoleUserSelectFormItem(temp, (roleUser != null)));
            }
        }

        model.addAttribute("selectRolesList", selectRolesList);
        model.addAttribute("userId", id);
        return "/page/auth/user/user_configRole";
    }


    @RequiresPermissions("auth_user:16")
    @ResponseBody
    @RequestMapping(value = "/saveRolesAjax", method = {RequestMethod.POST})
    public JsonData<Object> saveRoleAjax(@RequestParam(value = "roleId[]" ,required = false) String[] roleIds,String userId){
        RoleUser p1 = new RoleUser();
        p1.setUsers(userId);
        roleUserService.delete(new EntityWrapper<RoleUser>(p1));

        List<RoleUser> needSave = new ArrayList<RoleUser>();
        if (roleIds != null && roleIds.length>0) {
            for (String roleId : roleIds) {
                RoleUser roleUser = new RoleUser();
                roleUser.setUsers(userId);
                roleUser.setRoles(roleId);
                needSave.add(roleUser);
            }
        }
        if (CollectionUtils.isNotEmpty(needSave)) {
            roleUserService.insertBatch(needSave);
        }

        shiroRedisCacheManager.getCache(ShiroDataCacheAwareService.SHIRO_AUTHZATION_INFO_REDIS_CACHE_NAME ).clear();
        return new JsonData<Object>(JsonResponse.buildSuccess());
    }
}
