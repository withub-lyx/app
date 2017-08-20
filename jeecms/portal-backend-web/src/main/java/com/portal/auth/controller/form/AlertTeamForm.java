package com.portal.auth.controller.form;

import com.portal.auth.authdb.entity.AlertTeam;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.format.datetime.DateFormatter;

import java.util.Date;

/**
 * Created by liuquan on 2017/1/4 11:43
 */
public class AlertTeamForm {
    private AlertTeam alertTeam;


    private String realyCreaterName;
    public AlertTeamForm() {
    }

    public AlertTeamForm(AlertTeam alertTeam) {
        this.alertTeam = alertTeam;
        realyCreaterName  = alertTeam.getCreator();
    }

    public AlertTeamForm(AlertTeam temp, String name) {
        this.alertTeam = temp;
        realyCreaterName = name;
    }

    public AlertTeam getAlertTeam() {
        return alertTeam;
    }

    public void setAlertTeam(AlertTeam alertTeam) {
        this.alertTeam = alertTeam;
    }

    public void setAppid(Integer appid) {
        alertTeam.setAppid(appid);
    }

    public Integer getId() {
        return alertTeam.getId();
    }

    public void setId(Integer id) {
        alertTeam.setId(id);
    }

    public String getName() {
        return alertTeam.getName();
    }

    public void setName(String name) {
        alertTeam.setName(name);
    }
    public String getRealyName() {
        return realyCreaterName;
    }


    public String getCreator() {
        return alertTeam.getCreator();
    }

    public void setCreator(String creator) {
        alertTeam.setCreator(creator);
    }

    public Date getCreated() {
        return alertTeam.getCreated();
    }

    public String getCreatedDateDesc() {
        return DateFormatUtils.format(alertTeam.getCreated(), "yyyy-MM-dd HH:mm:ss");

    }

    public void setCreated(Date created) {
        alertTeam.setCreated(created);
    }

    public Integer getAppid() {
        return alertTeam.getAppid();
    }
}
