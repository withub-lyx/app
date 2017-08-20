package com.portal.auth.controller;

import com.portal.common.page.JsonData;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by liuquan on 2016/12/22 17:08
 */
@Controller
@RequestMapping(value = "/test")
public class TestLoginController {


    //@RequiresRoles("admin2")
    @RequiresPermissions("sub_menu_3code:4")

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @ResponseBody
    public JsonData testLogin(){
        return new JsonData("OK");
    }


    /**
     * http://www.portal.com:4444/test/400?a=x
     * @param a
     * @return
     */
    @RequestMapping(value = "/400",method = RequestMethod.GET)
    @ResponseBody
    public JsonData test400(int a){
        return new JsonData("OK");
    }


    /**
     * http://www.portal.com:4444/test/500
     * @return
     */
    @RequestMapping(value = "/500",method = RequestMethod.GET)
    @ResponseBody
    public JsonData test500(){
        if (true) {
            throw new RuntimeException("xx");
        }
        return new JsonData("OK");
    }



    @RequestMapping(value = "/permission-test",method = RequestMethod.GET)
    public String testPermision(){

        return  "/page/test1/test3/permission-test";
    }
}
