package com.portal.auth.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by liuquan on 2017/1/4 18:04
 */
@Controller
@RequestMapping(value = "")
public class LoginController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPageIndex(HttpServletRequest request) {
        request.getSession().setAttribute("liuquan","liuquan");

        return "/page/login-v1";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPageIndex(HttpServletRequest request, Model model) {
        String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
        String error = null;
        if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名不存在";
        } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        }else if (LockedAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户被锁定";
        } else if (exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        }


        model.addAttribute("error", error);
        System.out.println("登陆错误信息提示："+error);
        return "/page/login-v1";
    }
}
