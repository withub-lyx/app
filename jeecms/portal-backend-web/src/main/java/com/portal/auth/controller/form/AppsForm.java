package com.portal.auth.controller.form;

import com.baomidou.mybatisplus.mapper.SqlQuery;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.portal.auth.authdb.entity.Apps;
import com.portal.auth.constant.AppClassId;
import com.portal.auth.constant.AppFunc;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by liuquan on 2017/1/3 13:32
 */
public class AppsForm {
    private Apps app;

    public AppsForm() {
    }


    public AppsForm(Apps app) {
        this.app = app;
    }

    public Integer getAppid() {
        return app.getAppid();
    }

    public boolean deleteById() {
        return app.deleteById();
    }

    public void setAppname(String appname) {
        app.setAppname(appname);
    }

    public Integer getApptype() {
        return app.getApptype();
    }

    public SqlQuery sql() {
        return app.sql();
    }

    public String getAppname() {
        return app.getAppname();
    }

    public boolean updateById() {
        return app.updateById();
    }

    public void setAppnamecn(String appnamecn) {
        app.setAppnamecn(appnamecn);
    }

    public void setAppurl(String appurl) {
        app.setAppurl(appurl);
    }

    public void setAppclassid(Integer appclassid) {
        app.setAppclassid(appclassid);
    }

    public String getAppnamecn() {
        return app.getAppnamecn();
    }

    public String getAppurl() {
        return app.getAppurl();
    }


    public void setAppuser(String appuser) {
        app.setAppuser(appuser);
    }

    public Apps selectOne(Wrapper wrapper) {
        return app.selectOne(wrapper);
    }


    public void setAppid(Integer appid) {
        app.setAppid(appid);
    }

    public String getAppuser() {
        return app.getAppuser();
    }

    public boolean deleteById(Serializable id) {
        return app.deleteById(id);
    }

    public Apps selectById(Serializable id) {
        return app.selectById(id);
    }

    public Apps selectById() {
        return app.selectById();
    }


    public void setAddtime(Date addtime) {
        app.setAddtime(addtime);
    }

    public int selectCount(Wrapper wrapper) {
        return app.selectCount(wrapper);
    }


    public Integer getAppstatus() {
        return app.getAppstatus();
    }

    public List<Apps> selectAll() {
        return app.selectAll();
    }

    public List<Apps> selectList(String whereClause, Object... args) {
        return app.selectList(whereClause, args);
    }

    public Integer getAppclassid() {
        return app.getAppclassid();
    }


    public String getAppclassidStr() {
        AppClassId appClassId = AppClassId.getByCode(app.getAppclassid());

        return (null==appClassId)?"":appClassId.getDesc();
    }

    public Apps selectOne(String whereClause, Object... args) {
        return app.selectOne(whereClause, args);
    }

    public void setApptype(Integer apptype) {
        app.setApptype(apptype);
    }

    public void setAppstatus(Integer appstatus) {
        app.setAppstatus(appstatus);
    }

    public boolean insertOrUpdate() {
        return app.insertOrUpdate();
    }

    public List<Apps> selectList(Wrapper wrapper) {
        return app.selectList(wrapper);
    }


    public Date getAddtime() {
        return app.getAddtime();
    }
    public String getAddtimeStr() {
        String format = DateFormatUtils.format(app.getAddtime(), "yyyy-MM-dd HH:mm:ss");

        return format ;
    }


    public int selectCount(String whereClause, Object... args) {
        return app.selectCount(whereClause, args);
    }

    public String getAppfuns() {
        return app.getAppfuns();
    }

    public String getAppfunsStr() {
        String result= "";
        if (StringUtils.isBlank(app.getAppfuns())) {
            return "";
        }
        String[] split = app.getAppfuns().split(",");
        if (null != split) {
            for (String temp : split) {
                if (StringUtils.isNotBlank(temp)) {
                    AppFunc func = AppFunc.getByCode(Integer.valueOf(temp));
                    if (func != null) {
                       // result+=","+ func.getDesc()+;
                        result+=func.getDesc()+"<br>";
                    }
                }
            }

            if (result.indexOf(",") == 0) {
                result = result.substring(1, result.length());
            }
        }
        return result;
    }

    public void setAppfuns(String appfuns) {
        app.setAppfuns(appfuns);
    }
}
