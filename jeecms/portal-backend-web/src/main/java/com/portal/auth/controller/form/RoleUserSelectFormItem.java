package com.portal.auth.controller.form;

import com.portal.auth.authdb.entity.Roles;

/**
 * Created by liuquan on 2016/12/19 14:02
 */
public class RoleUserSelectFormItem {
    private Roles role;

    private boolean isSelect;


    public RoleUserSelectFormItem(Roles role, boolean isSelect) {
        this.role = role;
        this.isSelect = isSelect;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public void setAdduser(String adduser) {
        role.setAdduser(adduser);
    }

    public String getId() {
        return role.getId();
    }

    public void setId(String id) {
        role.setId(id);
    }

    public String getName() {
        return role.getName();
    }

    public void setName(String name) {
        role.setName(name);
    }

    public String getIsok() {
        return role.getIsok();
    }

    public void setIsok(String isok) {
        role.setIsok(isok);
    }

    public String getAdduser() {
        return role.getAdduser();
    }
}
