package com.portal.auth.constant;

/**
 * 所属事业部
 * Created by liuquan on 2017/1/3 11:41
 */

public enum AppApiStatu {
    NORMAL(0, "正常"),
    STATUE1(1, "状态1"),
    STATUE2(2, "状态2"),
    STATUE3(3, "状态3"),
    STATUE4(4, "状态4");

    private int code;

    private String desc;

    AppApiStatu(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static AppApiStatu getByCode(Integer appclassid) {
        if (null == appclassid) {
            return null;
        }
        for (AppApiStatu temp : AppApiStatu.values()) {
            if (temp.getCode() == appclassid) {
                return temp ;
            }
        }
        return null;
    }
}
