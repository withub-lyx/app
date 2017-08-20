//package com.portal.auth.controller;
//
//import com.baomidou.mybatisplus.plugins.Page;
//import com.portal.common.page.JsonData;
//import com.portal.auth.authdb.entity.DbTest;
//import com.portal.auth.authdb.service.IDbTestService;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.Date;
//import java.util.UUID;
//
///**
// * Created by Administrator on 2015/8/11 17:14
// */
//@Controller
//@RequestMapping("/test")
//public class TestController implements ApplicationContextAware {
//
//    private ApplicationContext applicationContext;
//
//
//    @Autowired
//    private IDbTestService dbTestService;
//
//    /**
//     * tiles3测试*/
//    @RequestMapping(value = "/page1",method = RequestMethod.GET)
//    public String jspTest(){
//        return "jsp-test.innerLayout";
//    }
//    @RequestMapping(value = "/page2",method = RequestMethod.GET)
//    public String jxxxxTest(){
//        return "jsp-test2.innerLayout";
//    }
//    @RequestMapping(value = "/page3",method = RequestMethod.GET)
//    public String packageTest(){
//        return "test1/test3/jsp-test3.innerLayout";
//    }
//
//    /**
//     * http://localhost:4444/test/dbtest/insert
//     */
//    @ResponseBody
//    @RequestMapping(value = "/dbtest/insert",method = RequestMethod.GET)
//    public JsonData dbtest(){
//        DbTest domain = new DbTest();
//        domain.setName(UUID.randomUUID().toString());
//        domain.setDate(new Date());
//         dbTestService.insert(domain);
//        return new JsonData(domain);
//    }
//
//
//    /**
//     * http://localhost:4444/test/dbtest/queryById?id=1
//     */
//    @ResponseBody
//    @RequestMapping(value = "/dbtest/queryById",method = RequestMethod.GET)
//    public JsonData queryById(Integer id){
//        return new JsonData(dbTestService.selectById(id));
//    }
//
//    /**
//     * http://localhost:4444/test/dbtest/deleteById?id=1
//     */
//    @ResponseBody
//    @RequestMapping(value = "/dbtest/deleteById",method = RequestMethod.GET)
//    public JsonData deleteByIdtest(Integer id){
//        boolean b = dbTestService.deleteById(id);
//        return new JsonData(b);
//    }
//
//    /**
//     * http://localhost:4444/test/dbtest/queryByPage?currnetPage=1&size=1
//     */
//    @ResponseBody
//    @RequestMapping(value = "/dbtest/queryByPage",method = RequestMethod.GET)
//    public JsonData queryByPage(Integer currnetPage,Integer size){
//        Page<DbTest> page = new Page<DbTest>(currnetPage,size);
//        Page<DbTest> result = dbTestService.selectPage(page);
//        return new JsonData(result);
//    }
//
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        this.applicationContext = applicationContext;
//    }
//}
