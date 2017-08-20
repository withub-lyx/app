package com.portal.wechart.share.util;

import com.alibaba.fastjson.JSONObject;
import com.portal.auth.shiro.utils.CookieUtils;
import com.portal.common.WebConstant;
import com.portal.common.util.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by liuquan on 2017/1/6.
 */
@SuppressWarnings("all")
public class WeChartUtil {
    public static final String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
    public static final String GET_JS_API_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi";

    public static void main(String[] args) {
        String accessToken = getAccessToken("wx64d8003b1a65e8b5", "f593b95b477b275c0fa43419c9018673");
        String jsApiTicket = getJsApiTicket(accessToken);
        System.out.println(jsApiTicket);
    }
    public static String  getAccessToken(String appid,String appSecret){
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(String.format(GET_ACCESS_TOKEN_URL, appid, appSecret), String.class, new HashMap<String, Object>());
        System.out.println("access_token_api_result:"+result);
        JSONObject jsonObject = JSONObject.parseObject(result);
        String access_token = (String)jsonObject.get("access_token");
        return access_token;
    }



    public static String  getJsApiTicket(String accessToken){
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(String.format(GET_JS_API_TICKET, accessToken), String.class, new HashMap<String, Object>());
        System.out.println("getJsApiTicket:"+result);
        JSONObject jsonObject = JSONObject.parseObject(result);
        String ticket = (String)jsonObject.get("ticket");
        return ticket;
    }

    public static boolean weChatIsLogined(){
        String weChatUserId = CookieUtils.getCookieValue(RequestContext.getRequest(), WebConstant.WECHAT_UT);
        return StringUtils.isNotBlank(weChatUserId);
    }

    public static String getWechatUser(){
        String weChatUserId = CookieUtils.getCookieValue(RequestContext.getRequest(), WebConstant.WECHAT_UT);
        return weChatUserId;
    }

}
