/*
package com.portal.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

*/
/**
 * Created by liuquan on 2016/12/23 10:48
 *//*

@Controller
@RequestMapping("/test/redis")
public class TestRedisController {

    @Autowired
    private RedisCacheManager redisCacheManagerShiro;

    @RequestMapping(value = "/test1",method = RequestMethod.GET)
    @ResponseBody
    public Object test1(){
        Cache cache1 = redisCacheManagerShiro.getCache("cache1");
        cache1.put("cache1-key1","value-1");

        Cache cache2 = redisCacheManagerShiro.getCache("cache2");
        cache2.put("cache2-key1","value-2");

        System.out.println(cache1.get("cache1-key1").get());
        System.out.println(cache1.get("cache2-key1"));
        System.out.println(cache2.get("cache1-key1"));
        System.out.println(cache2.get("cache2-key1").get());

        return "ok";
    }
}
*/
