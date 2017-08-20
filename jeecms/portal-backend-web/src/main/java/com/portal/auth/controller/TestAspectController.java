package com.portal.auth.controller;

import com.portal.auth.authdb.service.impl.TestAspectService;
import com.portal.common.log.OpLog;
import com.portal.common.log.OpLogParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by liuquan on 2017/1/6 10:39
 */
@Controller
@RequestMapping("/testAspect")
public class TestAspectController {

    @Autowired
    private TestAspectService testAspectService;

    @OpLog("type1")
    @ResponseBody
    @RequestMapping(value = "/test1",method = RequestMethod.GET)
    public String test1(@OpLogParam String a, String b, String c){
        tessss(new Date());
        if (true) {
            throw new RuntimeException("xxxxxxxxxxxx");
        }
        return "ok";
    }

    @RequestMapping(value = "/test2",method = RequestMethod.GET)
    public void test2(String a,String b,String c){
        tessss(new Date());
    }


    @RequestMapping(value = "/test3",method = RequestMethod.GET)
    public void test3(String a,String b,String c){
        testAspectService.serviceMethod1(new Date());
    }

    @OpLog("type2")
    public void tessss(Date date){
        System.out.println("tessss");
        testAspectService.serviceMethod1(date);
    }
}
