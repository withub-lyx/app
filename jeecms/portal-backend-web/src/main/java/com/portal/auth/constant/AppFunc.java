package com.portal.auth.constant;

/**
 * Created by liuquan on 2017/1/3 11:43
 */
public enum  AppFunc {
    FUN1(1, "SSO认证接入"),
    FUN2(2, "图形验证码接入"),
    FUN3(3, "任务中心接入"),
    FUN4(4, "短信接入");

    private int code;

    private String desc;

    AppFunc(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static AppFunc getByCode(Integer integer) {
        if (integer == null) {
            return null;
        }
        for (AppFunc temp : AppFunc.values()) {
            if (temp.getCode() == integer) {
                return temp ;
            }
        }
        return null;
    }
}
