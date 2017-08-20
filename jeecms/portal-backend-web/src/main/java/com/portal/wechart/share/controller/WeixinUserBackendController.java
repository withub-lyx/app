package com.portal.wechart.share.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.portal.auth.authdb.entity.*;
import com.portal.auth.authdb.service.*;
import com.portal.auth.authdb.service.impl.MatchUserResultService;
import com.portal.auth.authdb.service.impl.WeixinUserService;
import com.portal.auth.controller.form.IS_OK;
import com.portal.auth.shiro.utils.LoginUserUtil;
import com.portal.common.page.BootstrapTablePage;
import com.portal.common.page.BootstrapTablePageRequest;
import com.portal.common.page.JsonData;
import com.portal.common.page.JsonResponse;
import com.portal.wechart.share.controller.form.ArticleFrom;
import com.portal.wechart.share.controller.form.WeixinUserForm;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created by liuquan on 2017/1/7.
 */

@SuppressWarnings("all")
@Controller
@RequestMapping("/backend/matchResult")
public class WeixinUserBackendController {


    @Autowired
    private IMatchUserResultService matchUserResultService;

    @Autowired
    private IWeixinUserService weixinUserService;


    @Autowired
    private IProvinceService provinceService;

    @Autowired
    private ICityService cityService;

    @Autowired
    private ISchoolService schoolService;


    /**
     *     www.shuangxizhuangshi.com/backend/matchResult/listPage
     * @return
     */
    @RequestMapping(value = "/listPage", method = RequestMethod.GET)
    public String list(Model model) {

        model.addAttribute("statusSelect", WeiChatUserStatusEnum.values());
        return "/page/auth/weixinuser/weixin_user_list";
    }


    @ResponseBody
    @RequestMapping(value = "/pageJson", method = RequestMethod.GET)
    public JsonData<BootstrapTablePage> pageJson(BootstrapTablePageRequest<WeixinUser> pageRequest, WeixinUser queryParam) {


        Map<String, Province> allPrivinceMap = provinceService.getAllPrivinceMap(true);
        Map<String, City> allCityMap = cityService.getAllCityMap(true);
        Map<Integer, School> allSchoolIdMap = schoolService.getAllSchoolMap(true);

        Condition conditon = buildCondition(queryParam);
        pageRequest.setSort(WeixinUser.CREATE_TIME);
        pageRequest.setOrder("desc");
        Page<WeixinUser> selectPage = weixinUserService.selectPage(pageRequest.toPage(), conditon);


        List<WeixinUserForm> result = new ArrayList<WeixinUserForm>();
        if (CollectionUtils.isNotEmpty(selectPage.getRecords())) {
            for (WeixinUser temp : selectPage.getRecords()) {

                MatchUserResult queryTimesParam = new MatchUserResult();
                queryTimesParam.setMatchUserIds(temp.getId());
                int matchTimes = matchUserResultService.selectCount(new EntityWrapper<MatchUserResult>(queryTimesParam));


                result.add(new WeixinUserForm(temp,allPrivinceMap,allCityMap,allSchoolIdMap,matchTimes));
            }
        }

        return new JsonData<BootstrapTablePage>(new BootstrapTablePage(selectPage, result));
    }

    private Condition buildCondition(WeixinUser queryParam) {
        Condition conditon = new Condition();
        if (null !=queryParam.getStatus()) {
            conditon.eq(WeixinUser.STATUS, queryParam.getStatus());
        }


        return conditon;
    }

//
//    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
//    public String add(Artict artict, Model model) {
//        return saveArtical(artict, model);
//    }
//
//    @RequestMapping(value = "/editPage", method = RequestMethod.GET)
//    public String edit(Artict artict, Model model) {
//        return saveArtical(artict, model);
//    }
//
//    public String saveArtical(Artict artict, Model model) {
//        if (StringUtils.isNotBlank(artict.getId())) {
//            artict = artictService.selectById(artict.getId());
//            model.addAttribute("isEdit", "true");
//        }else{
//            model.addAttribute("isEdit", "false");
//        }
//        model.addAttribute("isOkSelect", IS_OK.values());
//        model.addAttribute("vo", artict);
//        return "/page/auth/artic/artict_save";
//    }
//
//
//
//
//    @ResponseBody
//    @RequestMapping(value = "/addAjax", method = {RequestMethod.POST})
//    public JsonData<Object> addAjax(Artict artict) {
//        return save(artict);
//    }
//
//
//    @ResponseBody
//    @RequestMapping(value = "/editAjax", method = {RequestMethod.POST})
//    public JsonData<Object> editAjax(Artict artict) {
//        return save(artict);
//    }
//
//
//    public JsonData<Object> save(Artict artict) {
//        if (StringUtils.isBlank(artict.getId())) {
//            artict.setCreateDate(new Date());
//            artict.setCreator(LoginUserUtil.getLoginUser());
//        }else{
//            artict.setUpdateDate(new Date());
//        }
//
//        artictService.insertOrUpdate(artict);
//        return new JsonData<Object>(JsonResponse.buildSuccess(artict));
//    }
//
    @ResponseBody
    @RequestMapping(value = "/updatestatusAjax", method = {RequestMethod.POST})
    public JsonData<Object> delete(Integer status,Integer userId) {


        try {


            if (status.intValue() == WeiChatUserStatusEnum.SUCCESS_AUDIT.getCode()) {
                WeixinUser weixinUser = weixinUserService.selectById(userId);
                weixinUser.setSnapshot(null);
                String jsonSnapshot = JSON.toJSON(weixinUser).toString();
                WeixinUser save = new WeixinUser();
                save.setId(userId);
                save.setSnapshot(jsonSnapshot);
                weixinUserService.updateById(save);
            }


            WeixinUser entity = new WeixinUser();
            entity.setId(userId);
            entity.setStatus(status);

            weixinUserService.updateById(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonData<Object>(JsonResponse.buildError(e.getMessage()));
        }


        return new JsonData<Object>(JsonResponse.buildSuccess());
    }
}
