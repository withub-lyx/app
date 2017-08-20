package com.portal.auth.controller;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.portal.auth.authdb.entity.Apps;
import com.portal.auth.authdb.service.IAppsService;
import com.portal.auth.constant.AppClassId;
import com.portal.auth.constant.AppFunc;
import com.portal.auth.constant.AppType;
import com.portal.auth.controller.form.AppsForm;
import com.portal.auth.controller.form.IS_OK;
import com.portal.common.page.BootstrapTablePage;
import com.portal.common.page.BootstrapTablePageRequest;
import com.portal.common.page.JsonData;
import com.portal.common.page.JsonResponse;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by liuquan on 2017/1/3 10:04
 */
@Controller
@RequestMapping(value = "/apps")
public class AppController {


    private Logger logger = Logger.getLogger(AppController.class);


    @Autowired
    private IAppsService appsService;


    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String listPage(){
        return     "/page/auth/app/app_list";
    }



    @ResponseBody
    @RequestMapping(value = "/pageJson", method = RequestMethod.GET)
    public JsonData<BootstrapTablePage> pageJson(BootstrapTablePageRequest<Apps> pageRequest, Apps queryParam) {
        Condition conditon = buildCondition(queryParam);
        Page<Apps> selectPage = appsService.selectPage(pageRequest.toPage(), conditon);


        List<AppsForm> resultList = new ArrayList<AppsForm>();
        if (CollectionUtils.isNotEmpty(selectPage.getRecords())) {
            for (Apps tempApp : selectPage.getRecords()) {
                resultList.add(new AppsForm(tempApp));
            }
        }


        return new JsonData<BootstrapTablePage>(new BootstrapTablePage(selectPage,resultList));
    }



    private Condition buildCondition(Apps queryParam) {
        Condition conditon = new Condition();

        if (null !=queryParam.getAppid()) {
            conditon.eq(Apps.APPID, queryParam.getAppid());
        }

        if (StringUtils.isNotBlank(queryParam.getAppname())) {
            conditon.like(Apps.APPNAME, queryParam.getAppname());
        }

        if (StringUtils.isNotBlank(queryParam.getAppnamecn())) {
            conditon.like(Apps.APPNAMECN, queryParam.getAppnamecn());
        }

        if (null !=queryParam.getAppstatus()) {
            conditon.eq(Apps.APPSTATUS, queryParam.getAppstatus());
        }
        return conditon;
    }










//    @RequiresPermissions("auth_user:2")
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String add(Apps apps, Model model) {
        return savePage(apps, model);
    }

//    @RequiresPermissions("auth_user:4")
    @RequestMapping(value = "/editPage", method = RequestMethod.GET)
    public String edit(Apps apps, Model model) {
        return savePage(apps, model);
    }

    public String savePage(Apps apps, Model model) {
        if (apps.getAppid()!=null) {
            apps = appsService.selectById(apps.getAppid());
        }


        List<Integer> funcs = new ArrayList<Integer>();
        String appfuns = apps.getAppfuns();
        if (StringUtils.isNotBlank(appfuns)) {
            String[] split = appfuns.split(",");
            for (String temp : split) {
                if (StringUtils.isNotBlank(temp)) {
                    funcs.add(Integer.valueOf(temp));
                }
            }
        }
        model.addAttribute("funcs", funcs);


        model.addAttribute("vo", apps);
        model.addAttribute("isOkSelect", IS_OK.values());
        model.addAttribute("appTypes", AppType.values());
        model.addAttribute("appClassIds", AppClassId.values());
        model.addAttribute("appFuncs", AppFunc.values());
        return "/page/auth/app/app_save";
    }





    //@RequiresPermissions("auth_user:2")
    @ResponseBody
    @RequestMapping(value = "/addAjax", method = {RequestMethod.POST})
    public JsonData<Object> addAjax(Apps apps,@RequestParam(value = "funList[]",required = false) String[] funcIds) {
        apps.setAddtime(new Date());
        return saveAjax(apps,funcIds);
    }


    //@RequiresPermissions("auth_user:4")
    @ResponseBody
    @RequestMapping(value = "/editAjax", method = {RequestMethod.POST})
    public JsonData<Object> editAjax(Apps apps,@RequestParam(value = "ids[]" ,required = false) String[] funcIds) {
        return saveAjax(apps,funcIds);
    }


    public JsonData<Object> saveAjax(Apps apps,String[] funcIds) {
        if (funcIds != null) {
            apps.setAppfuns(org.apache.commons.lang.StringUtils.join(funcIds, ","));
        }
        appsService.insertOrUpdate(apps);
        return new JsonData<Object>(JsonResponse.buildSuccess(apps));
    }


    @RequiresPermissions("auth_user:8")
    @ResponseBody
    @RequestMapping(value = "/deleteAjax", method = {RequestMethod.POST})
    public JsonData<Object> delete(@RequestParam(value = "ids[]" ,required = false) String[] ids) {
        try {
            appsService.deleteBatchIds(Arrays.asList(ids));
        } catch (Exception e) {
            logger.error("删除app错误", e);
            return  new JsonData<Object>(JsonResponse.buildError(e.getMessage()));
        }
        return new JsonData<Object>(JsonResponse.buildSuccess());
    }
}
