package com.portal.wechart.share.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by liuquan on 2017/1/8.
 */
@Controller
@RequestMapping("/")
public class WeChartVerfiy {
    @ResponseBody
    @RequestMapping("/MP_verify_jShnIz4fy2pXUN1y.txt")
    public String verify(HttpServletRequest request, HttpServletResponse response){
        return "jShnIz4fy2pXUN1y";
    }
}
