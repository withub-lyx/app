//package com.portal.auth.controller;
//
//import com.baomidou.mybatisplus.plugins.Page;
//import com.portal.common.page.JsonData;
//import com.portal.auth.authdb.entity.DbTestVacharId;
//import com.portal.auth.authdb.service.IDbTestVacharIdService;
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
//@RequestMapping("/test2")
//public class Test2Controller implements ApplicationContextAware {
//
//    private ApplicationContext applicationContext;
//
//
//    @Autowired
//    private IDbTestVacharIdService dbTestVacharIdService;
//
//
//
//    /**
//     * http://localhost:4444/test/dbtest/insert
//     */
//    @ResponseBody
//    @RequestMapping(value = "/dbtest/insert",method = RequestMethod.GET)
//    public JsonData dbtest(){
//        DbTestVacharId domain = new DbTestVacharId();
//        domain.setName(UUID.randomUUID().toString());
//        domain.setDate(new Date());
//        dbTestVacharIdService.insert(domain);
//        return new JsonData<Object>(domain);
//    }
//    /**
//     * http://localhost:4444/test/dbtest/queryById?id=1
//     */
//    @ResponseBody
//    @RequestMapping(value = "/dbtest/queryById",method = RequestMethod.GET)
//    public JsonData queryById(String id){
//        return new JsonData(dbTestVacharIdService.selectById(id));
//    }
//
//    /**
//     * http://localhost:4444/test/dbtest/deleteById?id=1
//     */
//    @ResponseBody
//    @RequestMapping(value = "/dbtest/deleteById",method = RequestMethod.GET)
//    public JsonData deleteByIdtest(String id){
//        boolean b = dbTestVacharIdService.deleteById(id);
//        return new JsonData(b);
//    }
//
//    /**
//     * http://localhost:4444/test/dbtest/queryByPage?currnetPage=1&size=1
//     */
//    @ResponseBody
//    @RequestMapping(value = "/dbtest/queryByPage",method = RequestMethod.GET)
//    public JsonData queryByPage(Integer currnetPage,Integer size){
//        Page<DbTestVacharId> page = new Page<DbTestVacharId>(currnetPage,size);
//        Page<DbTestVacharId> result = dbTestVacharIdService.selectPage(page);
//        return new JsonData(result);
//    }
//
//    /**
//     * http://localhost:4444/test2/dbtest/customSQL
//     * @param currnetPage
//     * @param size
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping(value = "/dbtest/customSQL",method = RequestMethod.GET)
//    public JsonData customSQL(Integer currnetPage,Integer size){
//       // Integer integer = dbTestVacharIdService.queryFromDual();
//        return new JsonData("1");
//    }
//
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        this.applicationContext = applicationContext;
//    }
//}
