package com.portal.auth.exceptionHandler;

import com.alibaba.fastjson.JSON;
import com.portal.auth.shiro.utils.LoginUserUtil;
import com.portal.common.log.LogConstant;
import com.portal.common.log.OpLogUtil;
import com.portal.common.page.JsonResponse;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("all")
@ControllerAdvice
public class DefaultExceptionHandler {
    private static final  Logger logger = Logger.getLogger(DefaultExceptionHandler.class);

    /**
     * 没有权限 异常
     */
    @ExceptionHandler({UnauthorizedException.class})
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView processUnauthenticatedException(HttpServletRequest request, UnauthorizedException e, HttpServletResponse response) {

        OpLogUtil.saveLog(LogConstant.EXCEPTION_AUTH,e.getMessage(),null, LoginUserUtil.getLoginUser());
        String lastPath = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")).toLowerCase();
        if (lastPath.contains("ajax")||lastPath.contains("json")||lastPath.contains("jsonp")) {
            try {
                response.setContentType("text/plain");
                response.getWriter().write(JSON.toJSON(JsonResponse.buildError("没有权限！")).toString());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return null;
        }




        ModelAndView mv = new ModelAndView();
        mv.addObject("exception", e.getMessage());
        mv.setViewName("/page/unauthorized");
        return mv;
    }


    /**
     * 捕获  500 400
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    //@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView internalServerError(Exception e) {
        logger.error(e,e);

        OpLogUtil.saveLog(LogConstant.EXCEPTION,"页面异常",e.toString(), LoginUserUtil.getLoginUser());
        ModelAndView mv = new ModelAndView();
        mv.addObject("exception", e.getMessage());
        mv.setViewName("/page/500");
        return mv;
    }
}