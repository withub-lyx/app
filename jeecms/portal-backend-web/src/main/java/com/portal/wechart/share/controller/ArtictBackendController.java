package com.portal.wechart.share.controller;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.portal.auth.authdb.entity.Artict;
import com.portal.auth.authdb.service.IArtictService;
import com.portal.auth.controller.form.IS_OK;
import com.portal.auth.shiro.utils.LoginUserUtil;
import com.portal.common.page.BootstrapTablePage;
import com.portal.common.page.BootstrapTablePageRequest;
import com.portal.common.page.JsonData;
import com.portal.common.page.JsonResponse;
import com.portal.wechart.share.controller.form.ArticleFrom;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by liuquan on 2017/1/7.
 */

@Controller
@RequestMapping("/backend/article")
public class ArtictBackendController {


    @Autowired
    private IArtictService artictService;


    @RequestMapping(value = "/listPage", method = RequestMethod.GET)
    public String list() {
        return "/page/auth/artic/artict_list";
    }


    @ResponseBody
    @RequestMapping(value = "/pageJson", method = RequestMethod.GET)
    public JsonData<BootstrapTablePage> pageJson(BootstrapTablePageRequest<Artict> pageRequest, Artict queryParam) {
        Condition conditon = buildCondition(queryParam);
        pageRequest.setSort(Artict.CREATE_DATE);
        pageRequest.setOrder("desc");
        Page<Artict> selectPage = artictService.selectPage(pageRequest.toPage(), conditon);


        List<ArticleFrom> result = new ArrayList<ArticleFrom>();
        if (CollectionUtils.isNotEmpty(selectPage.getRecords())) {
            for (Artict temp : selectPage.getRecords()) {
                result.add(new ArticleFrom(temp));
            }
        }

        return new JsonData<BootstrapTablePage>(new BootstrapTablePage(selectPage, result));
    }

    private Condition buildCondition(Artict queryParam) {
        Condition conditon = new Condition();
        if (StringUtils.isNotBlank(queryParam.getTitle())) {
            conditon.like(Artict.TITLE, queryParam.getTitle());
        }


        return conditon;
    }


    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String add(Artict artict, Model model) {
        return saveArtical(artict, model);
    }

    @RequestMapping(value = "/editPage", method = RequestMethod.GET)
    public String edit(Artict artict, Model model) {
        return saveArtical(artict, model);
    }

    public String saveArtical(Artict artict, Model model) {
        if (StringUtils.isNotBlank(artict.getId())) {
            artict = artictService.selectById(artict.getId());
            model.addAttribute("isEdit", "true");
        }else{
            model.addAttribute("isEdit", "false");
        }
        model.addAttribute("isOkSelect", IS_OK.values());
        model.addAttribute("vo", artict);
        return "/page/auth/artic/artict_save";
    }




    @ResponseBody
    @RequestMapping(value = "/addAjax", method = {RequestMethod.POST})
    public JsonData<Object> addAjax(Artict artict) {
        return save(artict);
    }


    @ResponseBody
    @RequestMapping(value = "/editAjax", method = {RequestMethod.POST})
    public JsonData<Object> editAjax(Artict artict) {
        return save(artict);
    }


    public JsonData<Object> save(Artict artict) {
        if (StringUtils.isBlank(artict.getId())) {
            artict.setCreateDate(new Date());
            artict.setCreator(LoginUserUtil.getLoginUser());
        }else{
            artict.setUpdateDate(new Date());
        }

        artictService.insertOrUpdate(artict);
        return new JsonData<Object>(JsonResponse.buildSuccess(artict));
    }

    @ResponseBody
    @RequestMapping(value = "/deleteAjax", method = {RequestMethod.GET,RequestMethod.POST})
    public JsonData<Object> delete(@RequestParam("ids[]") String[] ids) {
        artictService.deleteBatchIds(Arrays.asList(ids));
        return new JsonData<Object>(JsonResponse.buildSuccess());
    }
}
