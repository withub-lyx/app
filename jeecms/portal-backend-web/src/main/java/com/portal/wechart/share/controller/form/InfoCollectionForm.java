package com.portal.wechart.share.controller.form;

import com.baomidou.mybatisplus.annotations.TableField;
import com.portal.auth.authdb.entity.WeiChatUserStatusEnum;
import com.portal.auth.authdb.entity.WeixinUser;
import com.portal.wechart.share.util.WeChartUtil;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

/**
 * Created by liuquan on 2017/1/23 14:15
 */
public class InfoCollectionForm {
    private String nickName;

    private Integer age;

    private Integer sex;


    private String provinceId;

    private String cityId;

    private Integer schoolId;


    /**
     * 个人简介
     */
    private String introduction;


    /**
     * 想对她说的话
     */
    private String sayToHer;

    /**
     * 联系方式二维码
     */
    private String contactQrCode;

    private String qrCodeShowName;


    /**
     *
     * photoPicker

     photoImgShow
     photoImg
     * 形象照
     */
    private String photoImg;

    private String photoImgShow;


    private String weiChatId;

    private Integer id;


    private String province_id_after_school;
    /**
     * 城市
     */

    private String city_id_after_school;


    private Integer status;
    private String statusDesc;
    private String snapshot;


    public InfoCollectionForm() {
    }

    public InfoCollectionForm(WeixinUser weixinUser) {
        if (weixinUser == null) {
            return ;
        }

        this.setId(weixinUser.getId());
        this.setWeiChatId(weixinUser.getWeiChatId());
        this.setNickName(weixinUser.getNickname());
        this.setSex(weixinUser.getSex());
        this.setAge(weixinUser.getAge());
        this.setProvinceId(weixinUser.getProvinceId());
        this.setCityId(weixinUser.getCityId());

        this.setProvince_id_after_school(weixinUser.getProvince_id_after_school());
        this.setCity_id_after_school(weixinUser.getCity_id_after_school());

        this.setSchoolId(weixinUser.getSchoolId());
        this.setIntroduction(weixinUser.getIntroduction());
        this.setSayToHer(weixinUser.getSayToHer());


        String contactQrCodeImgUrl = weixinUser.getContactQrCodeImgUrl();
        this.setContactQrCode(contactQrCodeImgUrl);
        if (StringUtils.isNotBlank(contactQrCodeImgUrl)) {
            this.setQrCodeShowName(contactQrCodeImgUrl.substring(contactQrCodeImgUrl.indexOf("lol_lol")+7,contactQrCodeImgUrl.length()));
        }


        String photoUrl = weixinUser.getPhoto_img_url();
        this.setPhotoImg(photoUrl);
        if (StringUtils.isNotBlank(photoUrl)) {
            this.setPhotoImgShow(photoUrl.substring(photoUrl.indexOf("lol_lol")+7,photoUrl.length()));
        }


        this.setStatus(weixinUser.getStatus());
        if (weixinUser.getStatus() != null) {
            this.setStatusDesc(WeiChatUserStatusEnum.getByCode(this.getStatus()).getDesc());
        }
    }



    public WeixinUser toWeiXinUser(){
        WeixinUser weixinUser = new WeixinUser();
        weixinUser.setId(this.getId());
        weixinUser.setAge(this.getAge());
        weixinUser.setWeiChatId(WeChartUtil.getWechatUser());
        weixinUser.setNickname(this.getNickName());
        weixinUser.setSex(this.getSex());
        weixinUser.setProvinceId(this.getProvinceId());
        weixinUser.setCityId(this.getCityId());
        weixinUser.setSchoolId(this.getSchoolId());
        weixinUser.setIntroduction(this.getIntroduction());
        weixinUser.setSayToHer(this.getSayToHer());
        weixinUser.setContactQrCodeImgUrl(this.getContactQrCode());
        weixinUser.setPhoto_img_url(this.getPhotoImg());
        weixinUser.setCity_id_after_school(this.getCity_id_after_school());
        weixinUser.setProvince_id_after_school(this.getProvince_id_after_school());

        if (this.getId() == null) {
            weixinUser.setRecommendWeight(1L);
        }
        return weixinUser;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getSayToHer() {
        return sayToHer;
    }

    public void setSayToHer(String sayToHer) {
        this.sayToHer = sayToHer;
    }

    public String getContactQrCode() {
        return contactQrCode;
    }

    public void setContactQrCode(String contactQrCode) {
        this.contactQrCode = contactQrCode;
    }

    public String getWeiChatId() {
        return weiChatId;
    }

    public void setWeiChatId(String weiChatId) {
        this.weiChatId = weiChatId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQrCodeShowName() {
        return qrCodeShowName;
    }

    public void setQrCodeShowName(String qrCodeShowName) {
        this.qrCodeShowName = qrCodeShowName;
    }

    public String getProvince_id_after_school() {
        return province_id_after_school;
    }

    public void setProvince_id_after_school(String province_id_after_school) {
        this.province_id_after_school = province_id_after_school;
    }

    public String getCity_id_after_school() {
        return city_id_after_school;
    }

    public void setCity_id_after_school(String city_id_after_school) {
        this.city_id_after_school = city_id_after_school;
    }

    public String getPhotoImg() {
        return photoImg;
    }

    public void setPhotoImg(String photoImg) {
        this.photoImg = photoImg;
    }

    public String getPhotoImgShow() {
        return photoImgShow;
    }

    public void setPhotoImgShow(String photoImgShow) {
        this.photoImgShow = photoImgShow;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(String snapshot) {
        this.snapshot = snapshot;
    }


    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }
}
