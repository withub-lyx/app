
package com.portal.auth.controller;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.portal.auth.authdb.entity.*;
import com.portal.auth.authdb.service.*;
import com.portal.auth.controller.form.*;
import com.portal.auth.shiro.db.service.ShiroDataCacheAwareService;
import com.portal.common.page.BootstrapTablePage;
import com.portal.common.page.BootstrapTablePageRequest;
import com.portal.common.page.JsonData;
import com.portal.common.page.JsonResponse;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
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
@RequestMapping("/team")
public class TeamController {


    @Autowired
    private CacheManager shiroRedisCacheManager;

    @Autowired
    private IUsersService usersService;

    @Autowired
    private IRoleUserService roleUserService;

    @Autowired
    private IRolesService rolesService;


    @Autowired
    private IAlertTeamUserService alertTeamUserService;

    @Autowired
    private IAlertTeamService alertTeamService;


    /**
     * http://localhost:4444/user/listPage
     *
     * @return current
     * size
     */
    //@RequiresPermissions("auth_user:1")
    @RequestMapping(value = "/listPage", method = RequestMethod.GET)
    public String list() {
        return "/page/auth/team/team_list";
    }

    //@RequiresPermissions("auth_user:1")
    @ResponseBody
    @RequestMapping(value = "/pageJson", method = RequestMethod.GET)
    public JsonData<BootstrapTablePage> pageJson(BootstrapTablePageRequest<AlertTeam> pageRequest, AlertTeam queryParam) {
        Condition conditon = buildCondition(queryParam);
        Page<AlertTeam> selectPage = alertTeamService.selectPage(pageRequest.toPage(), conditon);
        List<AlertTeamForm> resultList = new ArrayList<AlertTeamForm>();
        if (CollectionUtils.isNotEmpty(selectPage.getRecords())) {
            for (AlertTeam temp : selectPage.getRecords()) {
                Users users = new Users();
                users.setLoginname(temp.getCreator());
                Users users1 = usersService.selectOne(new EntityWrapper<Users>(users));
                if (users1 != null) {
                     resultList.add(new AlertTeamForm(temp,users1.getName()));
                }else{
                     resultList.add(new AlertTeamForm(temp));
                }
            }
        }
        return new JsonData<BootstrapTablePage>(new BootstrapTablePage(selectPage,resultList));
    }

    private Condition buildCondition(AlertTeam queryParam) {
        Condition conditon = new Condition();
        if (StringUtils.isNotBlank(queryParam.getName())) {
            conditon.like(AlertTeam.NAME, queryParam.getName());
        }
        return conditon;
    }


    //@RequiresPermissions("auth_user:2")
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addPage(AlertTeam alertTeam, Model model) {
        return savePage(alertTeam, model);
    }

    //@RequiresPermissions("auth_user:4")
    @RequestMapping(value = "/editPage", method = RequestMethod.GET)
    public String editPage(AlertTeam alertTeam, Model model) {
        return savePage(alertTeam, model);
    }

    public String savePage(AlertTeam alertTeam, Model model) {
        if (alertTeam.getId()!=null) {
            alertTeam = alertTeamService.selectById(alertTeam.getId());
        }
        model.addAttribute("vo", alertTeam);
        return "/page/auth/team/team_save";
    }


    //@RequiresPermissions("auth_user:2")
    @ResponseBody
    @RequestMapping(value = "/addAjax", method = {RequestMethod.POST})
    public JsonData<Object> addAjax(AlertTeam alertTeam) {
        return savePage(alertTeam);
    }


    @RequiresPermissions("auth_user:4")
    @ResponseBody
    @RequestMapping(value = "/editAjax", method = {RequestMethod.POST})
    public JsonData<Object> editAjax(AlertTeam alertTeam) {
        return savePage(alertTeam);
    }


    public JsonData<Object> savePage(AlertTeam alertTeam) {
        if (null ==alertTeam.getId()) {
            alertTeam.setCreator(SecurityUtils.getSubject().getPrincipal().toString());
            alertTeam.setCreated(new Date());
        }
        alertTeamService.insertOrUpdate(alertTeam);


        return new JsonData<Object>(JsonResponse.buildSuccess());
    }


    //@RequiresPermissions("auth_user:8")
    @ResponseBody
    @RequestMapping(value = "/deleteAjax", method = {RequestMethod.POST})
    public JsonData<Object> delete(@RequestParam("ids[]") String[] ids) {
        alertTeamService.deleteBatchIds(Arrays.asList(ids));
        return new JsonData<Object>(JsonResponse.buildSuccess());
    }


    @RequestMapping(value = "/teamUser", method = RequestMethod.GET)
    public String teamUserPage(String id,Model model) {

        List<Users> userses = usersService.selectList(new Condition());


        AlertTeamUser p2 = new AlertTeamUser();
        p2.setTid(Integer.valueOf(id));
        List<AlertTeamUser> alertTeamUsers1 = alertTeamUserService.selectList(new EntityWrapper<AlertTeamUser>(p2));
        StringBuffer userCheckString = new StringBuffer("");
        if (CollectionUtils.isNotEmpty(alertTeamUsers1)) {
            for (AlertTeamUser temp : alertTeamUsers1) {
                userCheckString.append("-");
                userCheckString.append(temp.getUid());
                userCheckString.append("-");
            }
        }





        model.addAttribute("userCheckString", userCheckString);
        model.addAttribute("users", userses==null?new ArrayList<Users>():userses);
        model.addAttribute("teamId", id);
        System.out.println(userCheckString);
        return   "/page/auth/team/team_user";
    }

    @ResponseBody
    @RequestMapping(value = "/teamUserSaveAjax", method = RequestMethod.POST)
    public  JsonData<Object> saveTeamUserAjax(String teamId,@RequestParam(value = "userIds[]",required = false) String[] ids,Model model) {
        AlertTeamUser p1 = new AlertTeamUser();
        p1.setTid(Integer.valueOf(teamId));
        alertTeamUserService.delete(new EntityWrapper<AlertTeamUser>(p1));


        ArrayList<AlertTeamUser> alertTeamUsers = new ArrayList<AlertTeamUser>();
        if (ids != null) {
            for (String userId : ids) {
                AlertTeamUser alertTeamUser = new AlertTeamUser();
                alertTeamUser.setTid(Integer.valueOf(teamId));
                alertTeamUser.setUid(String.valueOf(userId));
                alertTeamUsers.add(alertTeamUser);
            }
        }
        alertTeamUserService.insertBatch(alertTeamUsers);

        return new JsonData<Object>(JsonResponse.buildSuccess());
    }

}
