package com.portal.auth.servlet;

import com.portal.common.Constant;
import com.portal.common.WebConstant;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLDecoder;

/**
 * Created by liuquan on 2017/1/7.
 */
public class UploadFileServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(UploadFileServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String servletPath = req.getRequestURI().replace("/static/get","");


        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //String baseDir = "/liuquan-dev/upload_file_save";
        ServletOutputStream outputStream = response.getOutputStream();
        String filepath = Constant.fileUploadRootDir + servletPath;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(filepath);
        } catch (FileNotFoundException e) {
            logger.error("图片请求路径为："+filepath+"需要decode!!!");
            //e.printStackTrace();
            filepath = URLDecoder.decode(filepath, "utf-8");
            fileInputStream = new FileInputStream(filepath);
        }
        resp.setHeader("Content-length",String.valueOf(fileInputStream.available()));
        IOUtils.copy(fileInputStream, outputStream);
        //System.out.println("图片路径"+filepath);
        outputStream.close();
        return ;
        //super.doGet(req, resp);

    }
}
