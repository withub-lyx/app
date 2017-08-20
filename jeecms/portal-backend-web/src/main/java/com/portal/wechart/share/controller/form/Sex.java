package com.portal.wechart.share.controller.form;

/**
 * Created by liuquan on 2017/1/20.
 */
public enum  Sex {
    MAN(0,"男"),
    WOMAN(1,"女");
    private int code ;
    private String cnName;

    Sex(int code, String cnName) {
        this.code = code;
        this.cnName = cnName;
    }

    public static  Sex getByCode(int code){
        for (Sex temp : Sex.values()) {
            if (temp.getCode() == code) {
                return temp;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }
}
