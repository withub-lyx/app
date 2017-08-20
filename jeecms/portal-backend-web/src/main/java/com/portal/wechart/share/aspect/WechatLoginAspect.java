package com.portal.wechart.share.aspect;

import com.portal.auth.authdb.service.wechat.WeChatService;
import com.portal.auth.shiro.utils.CookieUtils;
import com.portal.common.WebConstant;
import com.portal.common.util.RequestContext;
import com.portal.wechart.share.util.WeChartUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

/**
 * Created by liuquan on 2017/1/22 16:22
 */
@Component
@Aspect
public class WechatLoginAspect {


    @Value("${this_web_host}")
    private String this_web_host;

    @Autowired
    private WeChatService weChatService;

    @Around(value = "@annotation(needWechatLogin)")
    public Object funcAuth(ProceedingJoinPoint pjp, NeedWechatLogin needWechatLogin) throws Throwable {

        HttpServletResponse response = RequestContext.getResponse();
        HttpServletRequest request = RequestContext.getRequest();

        if (StringUtils.isNotBlank(request.getParameter("backendWeiChatUserId"))) {
            Object result = pjp.proceed();
            return result;
        }


        if (needWechatLogin != null && !WeChartUtil.weChatIsLogined()) {
            String directUrl = URLEncoder.encode(request.getRequestURL().toString(), "utf-8");
            String redirect_uri = this_web_host + request.getContextPath() + "/front/wechat/dealcode";
            String getCodeUrl = weChatService.getCodeUrl(URLEncoder.encode(redirect_uri, "utf-8"));
            //System.out.println(getCodeUrl);
            CookieUtils.setCookie(request, response, WebConstant.WECHAT_LOGIN_CALLBACK, directUrl, null, "/");
            //response.sendRedirect(getCodeUrl);
            return "redirect:" + getCodeUrl;
        } else {
            Object result = pjp.proceed();
            return result;
        }
    }
}
