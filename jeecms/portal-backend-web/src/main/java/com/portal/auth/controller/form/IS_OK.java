package com.portal.auth.controller.form;

/**
 * Created by liuquan on 2016/12/19 10:48
 */
public enum IS_OK {
    OK(1,"可用"),
    NOT_OK(0,"不可用");
    private int code;
    private String desc;

     IS_OK(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
