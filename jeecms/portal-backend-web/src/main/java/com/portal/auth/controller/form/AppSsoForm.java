package com.portal.auth.controller.form;

import com.portal.auth.authdb.entity.AppSso;
import com.portal.auth.authdb.entity.Apps;

/**
 * Created by liuquan on 2016/12/16 11:37
 */
public class AppSsoForm {
    private Integer id;

    private String appName;

    private String appType;

    private String host;

    private String logouturl;

    private String keyvalue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getLogouturl() {
        return logouturl;
    }

    public void setLogouturl(String logouturl) {
        this.logouturl = logouturl;
    }

    public String getKeyvalue() {
        return keyvalue;
    }

    public void setKeyvalue(String keyvalue) {
        this.keyvalue = keyvalue;
    }

    public static AppSsoForm build(Apps apps, AppSso temp) {
        AppSsoForm result = new AppSsoForm();
        result.setId(temp.getId());
        result.setAppName(apps.getAppnamecn()+"("+temp.getAppname()+")");
        result.setAppType(String.valueOf(temp.getType()));
        result.setHost(temp.getHost());
        result.setLogouturl(temp.getLogouturl());
        result.setKeyvalue(temp.getKeyvalue());
        return result;
    }
}
