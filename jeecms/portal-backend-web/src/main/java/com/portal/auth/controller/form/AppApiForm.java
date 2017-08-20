package com.portal.auth.controller.form;

import com.portal.auth.authdb.entity.AppApi;
import com.portal.auth.constant.AppApiStatu;

/**
 * Created by liuquan on 2017/1/3 16:17
 */
public class AppApiForm {
    private AppApi appApi = new AppApi();

    private String appName;

    public AppApiForm(AppApi appApi,String appName) {
        this.appName = appName;
        this.appApi = appApi;
    }

    public void setStatus(Integer status) {
        appApi.setStatus(status);
    }

    public Integer getId() {
        return appApi.getId();
    }

    public void setId(Integer id) {
        appApi.setId(id);
    }

    public Integer getAppid() {
        return appApi.getAppid();
    }


    public String getAppName() {
        return appName;
    }

    public void setAppid(Integer appid) {
        appApi.setAppid(appid);
    }

    public String getSecuritykey() {
        return appApi.getSecuritykey();
    }

    public void setSecuritykey(String securitykey) {
        appApi.setSecuritykey(securitykey);
    }

    public String getIps() {
        return appApi.getIps();
    }

    public void setIps(String ips) {
        appApi.setIps(ips);
    }

    public String getAppcallback() {
        return appApi.getAppcallback();
    }

    public void setAppcallback(String appcallback) {
        appApi.setAppcallback(appcallback);
    }

    public Integer getStatus() {
        return appApi.getStatus();
    }

    public String getStatusDesc() {
        Integer status = appApi.getStatus();
        if (null == status) {
            return "-";
        }
        AppApiStatu byCode = AppApiStatu.getByCode(status);
        return (null==byCode)? "-":byCode.getDesc();
    }
}
