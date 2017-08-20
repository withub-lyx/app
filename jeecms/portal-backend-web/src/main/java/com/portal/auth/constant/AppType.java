package com.portal.auth.constant;

/**
 * Created by liuquan on 2017/1/3 11:43
 */
public enum AppType {
    TYPE1(1, "内网应用"),
    TYPE2(2, "外网应用"),
    TYPE3(3, "移动应用");

    private int code;

    private String desc;

    AppType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static AppType getByCode(Integer integer) {
        if (integer == null) {
            return null;
        }
        for (AppType temp : AppType.values()) {
            if (temp.getCode() == integer) {
                return temp ;
            }
        }
        return null;
    }
}
