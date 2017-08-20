package com.portal.wechart.share.controller;

import com.portal.common.util.QRCodeUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by liuquan on 2017/1/7.
 */
@Controller
@RequestMapping("/front/qrcode")
public class QrcodeController {

    private Logger logger = Logger.getLogger(QrcodeController.class);


    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public void getImg(String content ,HttpServletRequest request, HttpServletResponse response) {
        try {
            QRCodeUtil.generateQRCodeToStream(content,response.getOutputStream());
        } catch (Exception e) {
            logger.error("生成二维码错误",e);
        }
    }
}
