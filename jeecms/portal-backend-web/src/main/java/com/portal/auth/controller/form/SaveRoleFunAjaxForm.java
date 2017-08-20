package com.portal.auth.controller.form;

import com.portal.auth.authdb.entity.RoleFunc;

/**
 * Created by liuquan on 2016/12/21 15:07
 */
public class SaveRoleFunAjaxForm {

    private RoleFunc[] roleFunc;


    private String roleId;

    public RoleFunc[] getRoleFunc() {
        return roleFunc;
    }

    public void setRoleFunc(RoleFunc[] roleFunc) {
        this.roleFunc = roleFunc;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
