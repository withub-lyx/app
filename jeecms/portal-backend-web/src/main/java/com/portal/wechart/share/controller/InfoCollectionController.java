package com.portal.wechart.share.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.portal.auth.authdb.entity.*;
import com.portal.auth.authdb.service.*;
import com.portal.common.log.OpLogUtil;
import com.portal.common.page.JsonData;
import com.portal.common.page.JsonResponse;
import com.portal.common.util.IPutils;
import com.portal.wechart.share.aspect.NeedWechatLogin;
import com.portal.wechart.share.controller.form.InfoCollectionForm;
import com.portal.wechart.share.controller.form.Sex;
import com.portal.wechart.share.util.WeChartUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liuquan on 2017/1/7.
 */
@SuppressWarnings("all")
@Controller
@RequestMapping(value = "/front/collectinfo")
public class InfoCollectionController {

    @Autowired
    private CommonService commonService;


    @Autowired
    private IProvinceService provinceService;

    @Autowired
    private ICityService cityService;

    @Autowired
    private ISchoolService schoolService;


    @Autowired
    private IWeixinUserService weixinUserService;


    @Autowired
    private IMatchUserResultService matchUserResultService;

    /**
     * www.shuangxizhuangshi.com/front/collectinfo/index
     *
     * @param request
     * @param ua
     * @return edit是否是修改信息
     */
    @NeedWechatLogin
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String page1(boolean edit, HttpServletRequest request, @RequestHeader("User-Agent") String ua, Model model) {

        model.addAttribute("isEdit", edit);

        OpLogUtil.saveLog("访问收集信息页", "["+ IPutils.getClientIP(request)+"]      "+request.getRequestURI() + "      " + ua);

        List<Province> allProvince = provinceService.getAllProvince(true);
        model.addAttribute("provinceSelect", allProvince);


        List<String> schoolPlace = schoolService.getAllPlace(true);
        model.addAttribute("schoolPlaceSelect", schoolPlace);

        model.addAttribute("sexSelect", Sex.values());

        List<String> ageScope = new ArrayList<String>();
        for (int i = 18; i < 40; i++) {
            ageScope.add(String.valueOf(i));
        }
        model.addAttribute("ageScope", ageScope);


        WeixinUser entity = new WeixinUser();
        entity.setWeiChatId(WeChartUtil.getWechatUser());
        WeixinUser weixinUser = weixinUserService.selectOne(new EntityWrapper<WeixinUser>(entity));
        if (weixinUser == null) {
            //weixinUser = new WeixinUser();

            weixinUser = new WeixinUser();
//            weixinUser.setNickname("liuquan-demo");
//            weixinUser.setIntroduction("setIntroductionsetIntroductionsetIntroductionsetIntroductionsetIntroduction");
//            weixinUser.setSayToHer("setSayToHersetSayToHersetSayToHersetSayToHer");
        } else {
            model.addAttribute("schoolPlace", schoolService.selectById(weixinUser.getSchoolId()).getPlace());
        }
        model.addAttribute("weixinUser", new InfoCollectionForm(weixinUser));

        return "/page/wechart/share/collection_info";
    }


    /**
     * www.shuangxizhuangshi.com/front/collectinfo/showInfo
     */

    @NeedWechatLogin
    @RequestMapping(value = "/showInfo", method = RequestMethod.GET)
    public String showInfo(HttpServletRequest request, Model model, String backendWeiChatUserId, boolean changeStatus, HttpServletResponse response) {
        WeixinUser entity = new WeixinUser();

        if (StringUtils.isBlank(backendWeiChatUserId)) {
            backendWeiChatUserId = WeChartUtil.getWechatUser();
            model.addAttribute("isBackend", false);
        } else {
            model.addAttribute("isBackend", true);
        }
        entity.setWeiChatId(backendWeiChatUserId);
        final WeixinUser weixinUser = weixinUserService.selectOne(new EntityWrapper<WeixinUser>(entity));


        if (weixinUser == null) {
            return "/page/wechart/share/no_info";
        }


        /**
         * 前端用户改变自己的状态
         */
        if (changeStatus) {
            final WeixinUser param = new WeixinUser();
            param.setId(weixinUser.getId());
            final Integer status = weixinUser.getStatus();
            if (status.intValue() == WeiChatUserStatusEnum.DISABLE.getCode()) {
                param.setStatus(WeiChatUserStatusEnum.WAITING_AUDIT.getCode());
                weixinUser.setStatus(WeiChatUserStatusEnum.WAITING_AUDIT.getCode());
            }else{
                param.setStatus(WeiChatUserStatusEnum.DISABLE.getCode());
                weixinUser.setStatus(WeiChatUserStatusEnum.DISABLE.getCode());
            }
            weixinUserService.updateById(param);
            try {
                response.sendRedirect(request.getContextPath()+"/front/collectinfo/showInfo");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        Province province = provinceService.selectOne(new EntityWrapper<Province>(new Province() {{
            setProvinceid(weixinUser.getProvinceId());
        }}));

        City city = cityService.selectOne(new EntityWrapper<City>(new City() {{
            setCityid(weixinUser.getCityId());
        }}));


        Province province_id_after_school = provinceService.selectOne(new EntityWrapper<Province>(new Province() {{
            setProvinceid(weixinUser.getProvince_id_after_school());
        }}));

        City city_id_after_school = cityService.selectOne(new EntityWrapper<City>(new City() {{
            setCityid(weixinUser.getCity_id_after_school());
        }}));

        School school = schoolService.selectById(weixinUser.getSchoolId());


        model.addAttribute("province", province);
        model.addAttribute("city", city);

        model.addAttribute("province_id_after_school", province_id_after_school);
        model.addAttribute("city_id_after_school", city_id_after_school);
        model.addAttribute("school", school);
        model.addAttribute("myinfo", new InfoCollectionForm(weixinUser));

        return "/page/wechart/share/showmyinfo";
    }

    @NeedWechatLogin
    @RequestMapping(value = "/saveInfo", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse saveInfo(InfoCollectionForm infoCollectionForm) {
        System.out.println(JSON.toJSON(infoCollectionForm));


        WeixinUser entity = infoCollectionForm.toWeiXinUser();

        Date nowTime = new Date();
        if (entity.getId() == null) {
            entity.setUpdate_time(nowTime);
            entity.setCreateTime(nowTime);
        } else {
            entity.setUpdate_time(nowTime);
        }

        /**
         * 修改后成待审核
         */
        entity.setStatus(WeiChatUserStatusEnum.WAITING_AUDIT.getCode());
        weixinUserService.insertOrUpdate(entity);
        return JsonResponse.buildSuccess("1");
    }


    @ResponseBody
    @RequestMapping(value = "/getCityByProvinceAjax", method = RequestMethod.GET)
    public JsonData<JsonResponse> getCityByProvinceAjax(String provinceId) {
        City cityParam = new City();
        cityParam.setProvinceid(provinceId);
        List<City> cities = cityService.selectList(new EntityWrapper<City>(cityParam));
        if (CollectionUtils.isEmpty(cities)) {
            cities = new ArrayList<City>();
        }
        return new JsonData<JsonResponse>(JsonResponse.buildSuccess(cities));
    }

    @ResponseBody
    @RequestMapping(value = "/getSchoolByPlace", method = RequestMethod.GET)
    public JsonData<JsonResponse> getSchoolByPlace(String place) {
        School schoolParam = new School();
        schoolParam.setPlace(place);
        List<School> schools = schoolService.selectList(new EntityWrapper<School>(schoolParam));
        if (CollectionUtils.isEmpty(schools)) {
            schools = new ArrayList<School>();
        }
        return new JsonData<JsonResponse>(JsonResponse.buildSuccess(schools));
    }


    /**
     * /front/collectinfo/search_result
     *
     * @return 1.年纪男比女大8岁范围以内 男比女小1岁范围以内
     * 2.男女配
     * 3.学校配（优先 配不到再城市配）
     * 4.城市配
     */
    @NeedWechatLogin
    @RequestMapping(value = "/search_result", method = RequestMethod.GET)
    public String search_result(HttpServletRequest request, @RequestHeader("User-Agent") String ua, Model model, boolean isTest) {
        OpLogUtil.saveLog("访问推荐朋友页", "["+ IPutils.getClientIP(request)+"]      "+request.getRequestURI() + "      " + ua);


        String nowUserId = WeChartUtil.getWechatUser();
        WeixinUser param = new WeixinUser();
        param.setWeiChatId(nowUserId);

        WeixinUser nowLoginUser = weixinUserService.selectOne(new EntityWrapper<WeixinUser>(param));


        /**
         * 如果当前登录用户没有信息，跳到个人信息的无信息页面去
         */
        if (nowLoginUser == null) {
            return "/page/wechart/share/no_info";
        }


        final WeixinUser dbSavedRecomandUser = getRecomendUser(nowLoginUser, isTest);
        if (dbSavedRecomandUser == null) {
            return "/page/wechart/share/no_result";
        }


        model.addAttribute("nowLoginUser", nowLoginUser);



        /**
         * 取快照
         *
         */
        final WeixinUser recomandUser = JSON.parseObject(dbSavedRecomandUser.getSnapshot(), WeixinUser.class);

        Province province = provinceService.selectOne(new EntityWrapper<Province>(new Province() {{
            setProvinceid(recomandUser.getProvinceId());
        }}));

        City city = cityService.selectOne(new EntityWrapper<City>(new City() {{
            setCityid(recomandUser.getCityId());
        }}));


        Province province_id_after_school = provinceService.selectOne(new EntityWrapper<Province>(new Province() {{
            setProvinceid(recomandUser.getProvince_id_after_school());
        }}));

        City city_id_after_school = cityService.selectOne(new EntityWrapper<City>(new City() {{
            setCityid(recomandUser.getCity_id_after_school());
        }}));

        School school = schoolService.selectById(recomandUser.getSchoolId());


        model.addAttribute("province", province);
        model.addAttribute("city", city);

        model.addAttribute("province_id_after_school", province_id_after_school);
        model.addAttribute("city_id_after_school", city_id_after_school);
        model.addAttribute("school", school);
        model.addAttribute("recommendUser", new InfoCollectionForm(recomandUser));


//        return "/page/wechart/share/search_result";
        return "/page/wechart/share/search_result2";

    }

    private void dealMatchReult(Integer nowUserId, WeixinUser recomandUser) {
        /**
         * 更新权重
         */
        recomandUser.setRecommendWeight(new Date().getTime());
        weixinUserService.updateById(recomandUser);

        /**
         * 添加匹配结果r
         */
        MatchUserResult entity = new MatchUserResult();
        entity.setUserId(nowUserId);
        entity.setMatchUserIds(recomandUser.getId());
        entity.setMatchTime(new Date());
        matchUserResultService.insert(entity);
    }


    public WeixinUser getRecomendUser(WeixinUser nowLoginUser, boolean isTest) {


        if (isTest) {
            return nowLoginUser;

        } else {


            /**
             * 先查看是否过期
             */
            Integer historyMatchResult = matchUserResultService.getHistoryMatchResult(nowLoginUser.getId());
            if (historyMatchResult != null) {
                System.out.println("没过期~！！！！");
                return weixinUserService.selectById(historyMatchResult);
            }


            List<Integer> excludeUserIds = matchUserResultService.getExcludeUserIds(nowLoginUser.getId());


            WeixinUser result;


            ArrayList<Integer> ageList = buildAge(nowLoginUser);


            for (Integer age : ageList) {

                Condition condition1 = new Condition();
                /**
                 * 年龄   男女  学校
                 */
                if (nowLoginUser.getSex() == Sex.MAN.getCode()) {
                    //condition1.between(WeixinUser.AGE, String.valueOf(nowLoginUser.getAge() - 8), String.valueOf(nowLoginUser.getAge() + 1));
                    condition1.eq(WeixinUser.SEX, Sex.WOMAN.getCode());
                } else {
                    //condition1.between(WeixinUser.AGE, String.valueOf(nowLoginUser.getAge() - 1), String.valueOf(nowLoginUser.getAge() + 8));
                    condition1.eq(WeixinUser.SEX, Sex.MAN.getCode());
                }

                condition1.eq(WeixinUser.SCHOOL_ID, nowLoginUser.getSchoolId());
                condition1.orderBy(WeixinUser.RECOMMEND_WEIGHT, true);

                if (CollectionUtils.isNotEmpty(excludeUserIds)) {
                    condition1.notIn(WeixinUser.ID, excludeUserIds);
                }


                condition1.eq(WeixinUser.AGE, age);
                condition1.eq(WeixinUser.STATUS, WeiChatUserStatusEnum.SUCCESS_AUDIT.getCode());

                List<WeixinUser> temp1 = weixinUserService.selectList(condition1);
                if (CollectionUtils.isNotEmpty(temp1)) {


                    if (!isTest) {
                        /**
                         * 处理匹配结果。插入数据
                         */
                        dealMatchReult(nowLoginUser.getId(), temp1.get(0));
                    }


                    return temp1.get(0);
                }
            }


            for (Integer age : ageList) {
                /**
                 * 年龄   男女  城市
                 */
                Condition condition2 = new Condition();
                if (nowLoginUser.getSex() == Sex.MAN.getCode()) {
                    //condition2.between(WeixinUser.AGE, String.valueOf(nowLoginUser.getAge() - 8), String.valueOf(nowLoginUser.getAge() + 1));
                    condition2.eq(WeixinUser.SEX, Sex.WOMAN.getCode());
                } else {
                    //condition2.between(WeixinUser.AGE, String.valueOf(nowLoginUser.getAge() - 1), String.valueOf(nowLoginUser.getAge() + 8));
                    condition2.eq(WeixinUser.SEX, Sex.MAN.getCode());
                }

                condition2.eq(WeixinUser.AGE, age);

                condition2.eq(WeixinUser.CITY_ID, nowLoginUser.getCityId());
                condition2.orderBy(WeixinUser.RECOMMEND_WEIGHT, true);

                if (CollectionUtils.isNotEmpty(excludeUserIds)) {
                    condition2.notIn(WeixinUser.ID, excludeUserIds);
                }

                condition2.eq(WeixinUser.STATUS, WeiChatUserStatusEnum.SUCCESS_AUDIT.getCode());
                List<WeixinUser> temp2 = weixinUserService.selectList(condition2);
                if (CollectionUtils.isNotEmpty(temp2)) {


                    if (!isTest) {
                        /**
                         * 处理匹配结果。插入数据
                         */
                        dealMatchReult(nowLoginUser.getId(), temp2.get(0));
                    }


                    return temp2.get(0);
                }
            }





            for (Integer age : ageList) {


                /**
                 * 年龄   男女
                 */
                Condition condition3 = new Condition();
                condition3.eq(WeixinUser.AGE, age);
                if (nowLoginUser.getSex() == Sex.MAN.getCode()) {
                    //condition3.between(WeixinUser.AGE, String.valueOf(nowLoginUser.getAge() - 8), String.valueOf(nowLoginUser.getAge() + 1));
                    condition3.eq(WeixinUser.SEX, Sex.WOMAN.getCode());
                } else {
                    //condition3.between(WeixinUser.AGE, String.valueOf(nowLoginUser.getAge() - 1), String.valueOf(nowLoginUser.getAge() + 8));
                    condition3.eq(WeixinUser.SEX, Sex.MAN.getCode());
                }

                condition3.orderBy(WeixinUser.RECOMMEND_WEIGHT, true);

                if (CollectionUtils.isNotEmpty(excludeUserIds)) {
                    condition3.notIn(WeixinUser.ID, excludeUserIds);
                }



                condition3.eq(WeixinUser.STATUS, WeiChatUserStatusEnum.SUCCESS_AUDIT.getCode());
                List<WeixinUser> temp3 = weixinUserService.selectList(condition3);
                if (CollectionUtils.isNotEmpty(temp3)) {


                    if (!isTest) {
                        /**
                         * 处理匹配结果。插入数据
                         */
                        dealMatchReult(nowLoginUser.getId(), temp3.get(0));
                    }


                    return temp3.get(0);
                }
            }

            return null;

        }
    }

    private ArrayList<Integer> buildAge(WeixinUser nowLoginUser) {
        ArrayList<Integer> ageList = new ArrayList<Integer>();


        /**
         * 男生
         */
        if (nowLoginUser.getSex() == Sex.MAN.getCode()) {

            ageList.add(nowLoginUser.getAge() - 1);
            ageList.add(nowLoginUser.getAge() - 2);
            ageList.add(nowLoginUser.getAge() - 3);
            ageList.add(nowLoginUser.getAge());
            ageList.add(nowLoginUser.getAge() - 4);
            ageList.add(nowLoginUser.getAge() - 5);
            ageList.add(nowLoginUser.getAge() - 6);
            ageList.add(nowLoginUser.getAge() - 7);
            ageList.add(nowLoginUser.getAge() - 8);
            ageList.add(nowLoginUser.getAge() + 1);


            /**
             * 女生
             */
        } else {
            ageList.add(nowLoginUser.getAge() + 1);
            ageList.add(nowLoginUser.getAge() + 2);
            ageList.add(nowLoginUser.getAge() + 3);
            ageList.add(nowLoginUser.getAge());
            ageList.add(nowLoginUser.getAge() + 4);
            ageList.add(nowLoginUser.getAge() + 5);
            ageList.add(nowLoginUser.getAge() + 6);
            ageList.add(nowLoginUser.getAge() + 7);
            ageList.add(nowLoginUser.getAge() + 8);
            ageList.add(nowLoginUser.getAge() - 1);
        }
        return ageList;
    }

}
