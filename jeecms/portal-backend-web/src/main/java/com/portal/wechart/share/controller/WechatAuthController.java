package com.portal.wechart.share.controller;

import com.alibaba.fastjson.JSON;
import com.portal.auth.authdb.service.wechat.GetPageAccessTokenResponse;
import com.portal.auth.authdb.service.wechat.WeChatService;
import com.portal.auth.shiro.utils.CookieUtils;
import com.portal.common.WebConstant;
import com.portal.wechart.share.aspect.NeedWechatLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by liuquan on 2017/1/22 15:23
 */
@Controller
@RequestMapping(value = "/front/wechat")
public class WechatAuthController {


    @Autowired
    private WeChatService weChatService;

    @Value("${this_web_host}")
    private String this_web_host;


    /**
     * http://www.shuangxizhuangshi.com/front/wechat/login?service=http%3A%2F%2Fwww.shuangxizhuangshi.com%2Fa%2Fb%2Fc
     *
     * @param rtnUrl
     * @param service
     * @param request
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(String rtnUrl, String service, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String directUrl = URLEncoder.encode("service", "utf-8");

        String redirect_uri = this_web_host + request.getContextPath() + "/front/wechat/dealcode";
        String getCodeUrl = weChatService.getCodeUrl(URLEncoder.encode(redirect_uri, "utf-8"));

        System.out.println(getCodeUrl);
        CookieUtils.setCookie(request, response, WebConstant.WECHAT_LOGIN_CALLBACK, directUrl, null, "/");
        return "redirect:" + getCodeUrl;
    }



    /**
     * @param code
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/dealcode", method = RequestMethod.GET)
    public String dealcode(String code, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        GetPageAccessTokenResponse pageAccessToken = weChatService.getPageAccessToken(code);
        System.out.println(JSON.toJSON(pageAccessToken));
        String openid = pageAccessToken.getOpenid();
        CookieUtils.setCookie(request, response, WebConstant.WECHAT_UT, openid, null, "/");
        String redirectUrl = CookieUtils.getCookieValue(request, WebConstant.WECHAT_LOGIN_CALLBACK);
        return "redirect:" + URLDecoder.decode(redirectUrl, "utf-8");
    }



    /*====================测试====================*/
    /**
     * http://www.shuangxizhuangshi.com/front/wechat/needLogin
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/needLogin", method = RequestMethod.GET)
    @ResponseBody
    @NeedWechatLogin
    public String needLogin(HttpServletRequest request) {
        System.out.println("1");
        return CookieUtils.getCookieValue(request, WebConstant.WECHAT_UT);
    }
    /**
     * http://www.shuangxizhuangshi.com/front/wechat/showWeChatUser
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/showWeChatUser", method = RequestMethod.GET)
    @ResponseBody
    public String showWeChatUser(HttpServletRequest request, HttpServletResponse response) {
        String wechartUser = CookieUtils.getCookieValue(request, WebConstant.WECHAT_UT);
        return wechartUser;
    }

    /**
     * http://www.shuangxizhuangshi.com/front/wechat/deleteCookie
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/deleteCookie", method = RequestMethod.GET)
    @ResponseBody
    public String deleteCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie(WebConstant.WECHAT_UT, "");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        CookieUtils.deleteCookie(request, response, cookie);
        return "ok";
    }


    /**
     * http://www.shuangxizhuangshi.com/front/wechat/addDemoCookie
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/addDemoCookie", method = RequestMethod.GET)
    @ResponseBody
    public String addDemoCookie(HttpServletRequest request, HttpServletResponse response) {
        CookieUtils.setCookie(request, response, WebConstant.WECHAT_UT, "demoLiuquan", null, "/");
        return "ok";
    }


    /**
     * http://www.shuangxizhuangshi.com/front/wechat/addDemoUCookie?u=
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/addDemoUCookie", method = RequestMethod.GET)
    @ResponseBody
    public String addDemoCookie(HttpServletRequest request, HttpServletResponse response,String u) {
        CookieUtils.setCookie(request, response, WebConstant.WECHAT_UT, u, null, "/");
        return "ok";
    }
}
