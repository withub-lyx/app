
package com.portal.auth.controller;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.portal.auth.authdb.entity.*;
import com.portal.auth.authdb.service.*;
import com.portal.auth.constant.AppApiStatu;
import com.portal.auth.controller.form.*;
import com.portal.auth.shiro.db.service.ShiroDataCacheAwareService;
import com.portal.common.page.BootstrapTablePage;
import com.portal.common.page.BootstrapTablePageRequest;
import com.portal.common.page.JsonData;
import com.portal.common.page.JsonResponse;
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
@RequestMapping("/appApi")
@SuppressWarnings("")
public class AppApiController {


    @Autowired
    private IAppApiService appApiService;

    @Autowired
    private IAppsService appsService;

    @Autowired
    private IApiUrlService apiUrlService;

    /**
     * http://localhost:4444/user/listPage
     *
     * @return current
     * size
     */
    //@RequiresPermissions("auth_user:1")
    @RequestMapping(value = "/listPage", method = RequestMethod.GET)
    public String listPage() {
        return "/page/auth/appApi/appApi_list";
    }

    //@RequiresPermissions("auth_user:1")
    @ResponseBody
    @RequestMapping(value = "/pageJson", method = RequestMethod.GET)
    public JsonData<BootstrapTablePage> pageJson(BootstrapTablePageRequest<AppApi> pageRequest, AppApi queryParam) {
        Condition conditon = buildCondition(queryParam);
        Page<AppApi> selectPage = appApiService.selectPage(pageRequest.toPage(), conditon);



        List<AppApiForm> resultList = new ArrayList<AppApiForm>();
        if (CollectionUtils.isNotEmpty(selectPage.getRecords())) {
            for (AppApi tempApp : selectPage.getRecords()) {
                //tempApp.getAppid();

                Apps apps = appsService.selectById(tempApp.getAppid());
                resultList.add(new AppApiForm(tempApp,apps.getAppnamecn()));
            }
        }
        return new JsonData<BootstrapTablePage>(new BootstrapTablePage(selectPage,resultList));
    }

    private Condition buildCondition(AppApi appApi) {
        Condition conditon = new Condition();
        if (null !=appApi.getAppid()) {
            conditon.eq(AppApi.APPID, appApi.getAppid());
        }
        return conditon;
    }


    //@RequiresPermissions("auth_user:2")
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addPage(AppApi appApi, Model model) {
        return savePage(appApi, model);
    }

    //@RequiresPermissions("auth_user:4")
    @RequestMapping(value = "/editPage", method = RequestMethod.GET)
    public String editPage(AppApi appApi, Model model) {
        return savePage(appApi, model);
    }

    public String savePage(AppApi appApi, Model model) {
        if (appApi.getId()!=null) {
            appApi = appApiService.selectById(appApi.getId());
        }

        model.addAttribute("appSelectList", appsService.selectList(new EntityWrapper<Apps>(new Apps())));
        model.addAttribute("appApiStatusSelectList", AppApiStatu.values());
        model.addAttribute("vo", appApi);
        return "/page/auth/appApi/appApi_save";
    }

//    private List<String> buildAppsSelectList() {
//        ArrayList<String> result = new ArrayList<String>();
//        List<Apps> appses = ;
//        if (CollectionUtils.isNotEmpty(appses)) {
//            for (Apps temp : appses) {
//                result.add(temp.getAppnamecn() + "(" + temp.getAppid() + ")");
//            }
//        }
//        return result;
//    }


    //@RequiresPermissions("auth_user:2")
    @ResponseBody
    @RequestMapping(value = "/addAjax", method = {RequestMethod.POST})
    public JsonData<Object> addAjax(AppApi appApi) {
        return saveAjax(appApi);
    }


    //@RequiresPermissions("auth_user:4")
    @ResponseBody
    @RequestMapping(value = "/editAjax", method = {RequestMethod.POST})
    public JsonData<Object> editAjax(AppApi appApi) {
        return saveAjax(appApi);
    }


    public JsonData<Object> saveAjax(AppApi appApi) {
        appApiService.insertOrUpdate(appApi);
//

        return new JsonData<Object>(JsonResponse.buildSuccess(appApi));
    }


    //@RequiresPermissions("auth_user:8")
    @ResponseBody
    @RequestMapping(value = "/deleteAjax", method = {RequestMethod.POST})
    public JsonData<Object> delete(@RequestParam("ids[]") String[] ids) {
        appApiService.deleteBatchIds(Arrays.asList(ids));
        return new JsonData<Object>(JsonResponse.buildSuccess());
    }


    @RequestMapping(value = "/urlAuth", method = {RequestMethod.GET})
    public String urlAuthPage(Model model,String id) {
        model.addAttribute("appId", id);
        return "/page/auth/appApi/appApi_urlAuth";
    }





    @SuppressWarnings("all")
    @ResponseBody
    @RequestMapping(value = "/urlAuthpageJson", method = RequestMethod.GET)
    public JsonData<BootstrapTablePage> urlAuthpageJson(BootstrapTablePageRequest<ApiUrl> pageRequest, ApiUrl queryParam) {
        Page<ApiUrl> selectPage = apiUrlService.selectPage(pageRequest.toPage(),new EntityWrapper<ApiUrl>(queryParam));

        return new JsonData<BootstrapTablePage>(new BootstrapTablePage(selectPage));
    }

    @ResponseBody
    @RequestMapping(value = "/saveApiUrlAjax", method = RequestMethod.POST)
    public JsonData<Object> saveApiUrlAjax(ApiUrl apiUrl) {
        apiUrlService.insertOrUpdate(apiUrl);
//

        return new JsonData<Object>(JsonResponse.buildSuccess());
    }


    @ResponseBody
    @RequestMapping(value = "/deleteUrlAjax", method = {RequestMethod.POST})
    public JsonData<Object> deleteUrlAjax(@RequestParam("ids[]") String[] ids) {
        apiUrlService.deleteBatchIds(Arrays.asList(ids));
        return new JsonData<Object>(JsonResponse.buildSuccess());
    }
}


