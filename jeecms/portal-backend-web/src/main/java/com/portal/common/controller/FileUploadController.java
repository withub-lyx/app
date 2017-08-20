package com.portal.common.controller;

import com.portal.common.Constant;
import com.portal.common.WebConstant;
import org.apache.commons.io.IOUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

/**
 * Created by liuquan on 2017/1/11 10:10
 */
@Controller
@RequestMapping(value = "/front/fileUpload")
public class FileUploadController {

    /**
     * www.shuangxizhuangshi.com/front/fileUpload/demo
     * localhost/front/fileUpload/demo
     *
     *
     *
     * @return
     */
    @RequestMapping(value = "/demo",method = RequestMethod.GET)
    public String demo(){

        return "/page/common/fileUpload/demo";
    }


    @ResponseBody
    @RequestMapping(value = "/uploadTest",method = {RequestMethod.GET,RequestMethod.POST,RequestMethod.OPTIONS})
    public String uploadTest(String name ,@Param("file") MultipartFile file, HttpServletRequest request, HttpServletResponse respons) throws IOException {
        System.out.println("uploadTest"+name);

        int i = name.lastIndexOf(".");
        if (i < 0) {
            name=UUID.randomUUID().toString()+"."+ ImageIO.getImageReaders(ImageIO.createImageInputStream(file.getInputStream())).next().getFormatName();
            i = name.lastIndexOf(".");
            //return "error";
        }
        String suffix = name.substring(i, name.length());


        String fileName = UUID.randomUUID().toString()+"lol_lol"+name.substring(0,i)+ suffix;
        String filePath = "/webuploader/" + fileName;
        File file1 = new File(Constant.fileUploadRootDir + filePath);

        if (!file1.exists()) {
            file1.getParentFile().mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file1);

        System.out.println("保存的文件路径"+file1.getCanonicalPath());
        InputStream inputStream = file.getInputStream();
        IOUtils.copy(inputStream, fileOutputStream);
        IOUtils.closeQuietly(inputStream);
        IOUtils.closeQuietly(fileOutputStream);
        return "/static/get"+filePath;
    }
}
