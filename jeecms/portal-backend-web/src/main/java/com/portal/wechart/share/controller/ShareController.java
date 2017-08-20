package com.portal.wechart.share.controller;

import com.portal.auth.authdb.entity.Artict;
import com.portal.auth.authdb.service.IArtictService;
import com.portal.common.log.OpLogUtil;
import com.portal.common.page.JsonResponse;
import com.portal.common.util.IPutils;
import com.portal.wechart.share.ueditor.ActionEnter;
import com.portal.wechart.share.util.Sign;
import com.portal.wechart.share.util.WeChartUtil;
import net.sf.ehcache.Ehcache;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Map;

/**
 * Created by liuquan on 2017/1/6.
 */
@Controller
@RequestMapping(value = "/front/share")
public class ShareController {

    @Autowired
    private CacheManager springEhCacheCacheManager;

    @Value("${wx_appID}")
    private String appId;

    @Value("${wx_appsecret}")
    private String appSecret;

    @Autowired
    private IArtictService artictService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String test1(HttpServletRequest request,@RequestHeader("User-Agent") String ua, @PathVariable("id") String articleId, Model model) {



        OpLogUtil.saveLog("访问文章","["+ IPutils.getClientIP(request)+"]      "+request.getRequestURI()+"      "+ua);


        Artict artict = artictService.selectById(articleId);
        if (artict == null) {
            throw new RuntimeException("该页面不存在");
        }


        if (artict.getShowFront() == 0) {
            artict.setTitle("敬请期待");
            artict.setContent("<p style=\"text-align: center;\"><h5 style=\"text-align: center;\">敬请期待</h5></p>");
        }
        model.addAttribute("artict", artict);
        return "/page/wechart/share/share_page1";
    }


//    @ResponseBody
//    @RequestMapping(value = "/xx", method = RequestMethod.GET)
//    public void getWechatParam() {
//        Cache wechatCache = springEhCacheCacheManager.getCache("test");
//
//        while (true) {
//            Cache.ValueWrapper a = wechatCache.get("a");
//            if (a == null || a.get() == null) {
//                wechatCache.put("a","b");
//                System.out.println("放入"+ DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
//            }
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    @ResponseBody
    @RequestMapping(value = "/getWechatParam", method = RequestMethod.POST)
    public JsonResponse getWechatParam(String url) {
//        int endIndex = url.indexOf("?");
//        if (endIndex != -1) {
//            url = url.substring(0, endIndex);
//        }
        Map<String, String> signInfo;


        Cache wechatCache = springEhCacheCacheManager.getCache("wechat_ticket");

        Cache.ValueWrapper ticket = wechatCache.get("ticket_"+url);
        if (ticket != null && ticket.get() != null) {
            signInfo = (Map<String, String>) ticket.get();
        } else {
            String accessToken = WeChartUtil.getAccessToken(appId, appSecret);
            String jsApiTicket = WeChartUtil.getJsApiTicket(accessToken);
            signInfo = Sign.sign(jsApiTicket, url);
            signInfo.put("appId", appId);
            wechatCache.put("ticket_"+url, signInfo);
        }
        return JsonResponse.buildSuccess(signInfo);

    }

    /**
     * 百度编辑器测试页
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/2", method = RequestMethod.GET)
    public String ue_edit_page(Model model) {

        return "/page/wechart/share/ue_edit";
    }


    /**
     * 百度编辑器上传后台设置
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/3", method = {RequestMethod.GET, RequestMethod.POST})
    public void ue_edit_page(HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            request.setCharacterEncoding("utf-8");
            response.setHeader("Content-Type", "text/html");
            PrintWriter writer = response.getWriter();
            //String realPath = request.getSession().getServletContext().getRealPath("/");
            String rootPath = "/liuquan-dev/upload_file_save/";
            //System.out.println(rootPath);
            writer.write(new ActionEnter(request, rootPath).exec());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




}
