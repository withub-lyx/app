package com.portal.wechart.share.controller.form;

import com.portal.auth.authdb.entity.City;
import com.portal.auth.authdb.entity.Province;
import com.portal.auth.authdb.entity.School;
import com.portal.auth.authdb.entity.WeixinUser;
import org.apache.commons.lang.time.DateFormatUtils;

import java.util.Date;
import java.util.Map;

/**
 * Created by liuquan on 2017/2/6.
 */
public class WeixinUserForm {
    private WeixinUser weixinUser = new WeixinUser();

    private Integer matchTime;


    private Map<String, Province> allPrivinceMap;
    private Map<String, City> allCityMap;
    private Map<Integer, School> allSchoolIdMap;
    private Integer matchTimes;


    public WeixinUserForm(WeixinUser temp, Map<String, Province> allPrivinceMap, Map<String, City> allCityMap, Map<Integer, School> allSchoolIdMap,Integer matchTimes) {
        this.weixinUser = temp;
        this.allPrivinceMap = allPrivinceMap;
        this.allCityMap =allCityMap;
        this.allSchoolIdMap = allSchoolIdMap;
        this.matchTimes  = matchTimes;

    }

    public Integer getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(Integer matchTime) {
        this.matchTime = matchTime;
    }

    public WeixinUserForm() {
    }



    public Integer getId() {
        return weixinUser.getId();
    }

    public void setId(Integer id) {
        weixinUser.setId(id);
    }

    public String getWeiChatId() {
        return weixinUser.getWeiChatId();
    }

    public void setWeiChatId(String weiChatId) {
        weixinUser.setWeiChatId(weiChatId);
    }

    public String getNickname() {
        return weixinUser.getNickname();
    }

    public void setNickname(String nickname) {
        weixinUser.setNickname(nickname);
    }

    public Integer getSex() {
        return weixinUser.getSex();
    }

    public String getSexDesc() {
        Sex sex = Sex.getByCode(weixinUser.getSex());
        if (sex == null) {
            return "未知";
        }
        return sex.getCnName();
    }

    public void setSex(Integer sex) {
        weixinUser.setSex(sex);
    }

    public String getProvinceId() {
        return weixinUser.getProvinceId();
    }

    public String getProvinceDesc() {
        return allPrivinceMap.get(weixinUser.getProvinceId()).getProvincename();
    }

    public void setProvinceId(String provinceId) {
        weixinUser.setProvinceId(provinceId);
    }

    public String getCityId() {
        return weixinUser.getCityId();
    }

    public String getCityDesc() {
        return allCityMap.get(weixinUser.getCityId()).getCityname();
    }

    public void setCityId(String cityId) {
        weixinUser.setCityId(cityId);
    }

    public Integer getSchoolId() {
        return weixinUser.getSchoolId();
    }
    public String getSchoolDesc() {
        return allSchoolIdMap.get(weixinUser.getSchoolId()).getName();
    }
    public void setSchoolId(Integer schoolId) {
        weixinUser.setSchoolId(schoolId);
    }

    public String getIntroduction() {
        return weixinUser.getIntroduction();
    }

    public void setIntroduction(String introduction) {
        weixinUser.setIntroduction(introduction);
    }

    public String getSayToHer() {
        return weixinUser.getSayToHer();
    }

    public void setSayToHer(String sayToHer) {
        weixinUser.setSayToHer(sayToHer);
    }

    public String getContactQrCodeImgUrl() {
        return weixinUser.getContactQrCodeImgUrl();
    }

    public void setContactQrCodeImgUrl(String contactQrCodeImgUrl) {
        weixinUser.setContactQrCodeImgUrl(contactQrCodeImgUrl);
    }

    public Date getCreateTime() {
        return weixinUser.getCreateTime();
    }

    public String getCreateTimeDesc() {
        return DateFormatUtils.format(weixinUser.getCreateTime(),"yyyy-MM-dd  HH:mm:ss");
    }

    public void setCreateTime(Date createTime) {
        weixinUser.setCreateTime(createTime);
    }

    public Long getRecommendWeight() {
        return weixinUser.getRecommendWeight();
    }

    public void setRecommendWeight(Long recommendWeight) {
        weixinUser.setRecommendWeight(recommendWeight);
    }

    public Integer getAge() {
        return weixinUser.getAge();
    }

    public void setAge(Integer age) {
        weixinUser.setAge(age);
    }

    public String getProvince_id_after_school() {
        return weixinUser.getProvince_id_after_school();
    }


    public String getProvince_id_after_schoolDesc() {
        return allPrivinceMap.get(weixinUser.getProvince_id_after_school()).getProvincename();
    }

    public void setProvince_id_after_school(String province_id_after_school) {
        weixinUser.setProvince_id_after_school(province_id_after_school);
    }

    public String getCity_id_after_school() {
        return weixinUser.getCity_id_after_school();
    }

    public String getCity_id_after_school_desc() {
        return allCityMap.get(weixinUser.getCity_id_after_school()).getCityname();
    }

    public void setCity_id_after_school(String city_id_after_school) {
        weixinUser.setCity_id_after_school(city_id_after_school);
    }

    public String getPhoto_img_url() {
        return weixinUser.getPhoto_img_url();
    }

    public void setPhoto_img_url(String photo_img_url) {
        weixinUser.setPhoto_img_url(photo_img_url);
    }

    public Date getUpdate_time() {
        return weixinUser.getUpdate_time();
    }

    public String getUpdate_timeDesc() {
        return DateFormatUtils.format(weixinUser.getUpdate_time(),"yyyy-MM-dd  HH:mm:ss");
    }


    public void setUpdate_time(Date update_time) {
        weixinUser.setUpdate_time(update_time);
    }

    public Integer getMatchTimes(){
        return this.matchTimes;

    }


    public Integer getStatus() {
        return weixinUser.getStatus();
    }

    public void setStatus(Integer status) {
        weixinUser.setStatus(status);
    }

    public String getSnapshot() {
        return weixinUser.getSnapshot();
    }

    public void setSnapshot(String snapshot) {
        weixinUser.setSnapshot(snapshot);
    }
}
