package com.portal.wechart.share.controller;

import com.portal.common.log.OpLogUtil;
import com.portal.common.util.IPutils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by liuquan on 2017/1/8.
 */
@Controller
@RequestMapping("/front/log")
public class LogController {
    @ResponseBody
    @RequestMapping(value = "/sharePengYouQuan",method = RequestMethod.GET)
    public void shareLog(HttpServletRequest request,@RequestHeader("User-Agent") String ua  ,@RequestHeader("User-Agent") String Referer){
        OpLogUtil.saveLog("分享朋友圈","["+ IPutils.getClientIP(request)+"]      "+Referer+"      "+ua);
    }


    @RequestMapping(value = "/sharePengYou",method = RequestMethod.GET)
    public void sharePengYou(HttpServletRequest request,@RequestHeader("User-Agent") String ua  ,@RequestHeader("User-Agent") String Referer){
        OpLogUtil.saveLog("分享朋友/群","["+ IPutils.getClientIP(request)+"]      "+Referer+"      "+ua);
    }
}
