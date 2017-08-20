package com.portal.auth.constant;

/**
 * 所属事业部
 * Created by liuquan on 2017/1/3 11:41
 */

public enum  AppClassId {
    CLASS1(1, "事业部1"),
    CLASS2(2, "事业部2"),
    CLASS3(3, "事业部3"),
    CLASS4(4, "事业部4");

    private int code;

    private String desc;

    AppClassId(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static AppClassId getByCode(Integer appclassid) {
        if (null == appclassid) {
            return null;
        }
        for (AppClassId temp : AppClassId.values()) {
            if (temp.getCode() == appclassid) {
                return temp ;
            }
        }
        return null;
    }
}
