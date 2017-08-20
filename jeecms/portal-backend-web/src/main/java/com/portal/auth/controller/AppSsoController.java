package com.portal.auth.controller;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.portal.common.page.BootstrapTablePage;
import com.portal.common.page.BootstrapTablePageRequest;
import com.portal.common.page.JsonData;
import com.portal.common.page.JsonResponse;
import com.portal.auth.controller.form.AppSsoForm;
import com.portal.auth.authdb.entity.AppSso;
import com.portal.auth.authdb.entity.Apps;
import com.portal.auth.authdb.service.IAppSsoService;
import com.portal.auth.authdb.service.IAppsService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by liuquan on 2016/12/14 14:28
 */
@Controller
@RequestMapping("/appsso")
public class AppSsoController {


    @Autowired
    private IAppSsoService appSsoService;

    @Autowired
    private IAppsService appsService;

    /**
     * http://localhost:4444/appsso/list
     *
     * @return current
     * size
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Page<AppSso> page) {
        return "/page/auth/appsso/appsso_list";
    }

    @ResponseBody
    @RequestMapping(value = "/pageJson", method = RequestMethod.GET)
    public JsonData<BootstrapTablePage> pageJson(BootstrapTablePageRequest<AppSso> pageRequest, AppSso queryParam) {
        Condition conditon = buildCondition(queryParam);

        Page<AppSso> selectPage = appSsoService.selectPage(pageRequest.toPage(), conditon);

        List<AppSsoForm> result = new ArrayList<AppSsoForm>();
        List<AppSso> records = selectPage.getRecords();
        if (CollectionUtils.isNotEmpty(records)) {
            for (AppSso temp : records) {
                Apps apps = appsService.selectById(temp.getAppid());
                result.add(AppSsoForm.build(apps, temp));
            }
        }


        return new JsonData<BootstrapTablePage>(new BootstrapTablePage(selectPage,result));
    }

    private Condition buildCondition(AppSso queryParam) {
        Condition conditon = new Condition();
        if (StringUtils.isNotBlank(queryParam.getHost())) {
            conditon.like(AppSso.HOST, queryParam.getHost());
        }
        if (queryParam.getAppid() != null) {
            conditon.eq(AppSso.APPID, queryParam.getAppid());
        }
        return conditon;
    }

    @RequestMapping(value = "/savePage", method = RequestMethod.GET)
    public String add(AppSso appSso, Model model) {
        List<Apps> appses = buildSelectItem();


        model.addAttribute("apps", appses);
        if (appSso.getId()!=null) {
            appSso = appSsoService.selectById(appSso.getId());
        }
        model.addAttribute("vo", appSso);
        return "/page/auth/appsso/appsso_add";
    }

    private List<Apps> buildSelectItem() {
        List<Apps> appses = appsService.selectList(new EntityWrapper<Apps>(new Apps()));
        if (CollectionUtils.isNotEmpty(appses)) {
            for (Apps temp : appses) {
                temp.setAppnamecn(temp.getAppname()+"("+temp.getAppid()+")");
            }
        }
        return appses;
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public JsonData<Object> save(AppSso appSso) {

        generateObj(appSso);
        appSsoService.insertOrUpdate(appSso);
        return new JsonData<Object>(JsonResponse.buildSuccess(appSso));
    }

    private void generateObj(AppSso appSso) {
        if (appSso.getId() != null) {
            return ;
        }
        AppSso condition = new AppSso();
        condition.setAppid(appSso.getAppid());
        List<AppSso> existNowAppSso = appSsoService.selectList(new EntityWrapper<AppSso>(condition));
        if (CollectionUtils.isNotEmpty(existNowAppSso)) {
            appSso.setKeyvalue(existNowAppSso.get(0).getKeyvalue());
            appSso.setKeysign(existNowAppSso.get(0).getKeysign());
            appSso.setKeyid(existNowAppSso.get(0).getKeyid());
        }else{
            appSso.setKeyid(appSso.getAppid());
            appSso.setKeyvalue(UUID.randomUUID().toString().substring(0,6));
            appSso.setKeysign(UUID.randomUUID().toString().substring(24,30));
        }

        Apps app = appsService.selectById(appSso.getAppid());
        appSso.setAppname(app.getAppname());
        appSso.setType(app.getApptype());
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public JsonData<Object> delete(@RequestParam("ids[]") String[] ids) {
        appSsoService.deleteBatchIds(Arrays.asList(ids));
        return new JsonData<Object>(JsonResponse.buildSuccess());
    }

    @ResponseBody
    @RequestMapping(value = "/alreadyAuth", method = {RequestMethod.GET})
    public JsonData<List<AppSso>> delete(Integer appId) {
        AppSso queryCondition = new AppSso();
        queryCondition.setAppid(appId);

        List<AppSso> appSsos = appSsoService.selectList(new EntityWrapper<AppSso>(queryCondition));
        return new JsonData<List<AppSso>>(appSsos);
    }
}
