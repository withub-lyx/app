package com.portal.auth.controller.form;

/**
 * Created by liuquan on 2016/12/19 11:29
 */
public enum  SEX {
    MAN(1,"男"), WOMAN(0, "女");

    private int code;

    private String desc;

    private SEX(int code, String desc) {
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
