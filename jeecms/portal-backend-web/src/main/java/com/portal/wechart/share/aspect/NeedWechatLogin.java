package com.portal.wechart.share.aspect;

import java.lang.annotation.*;

/**
 * Created by liuquan on 2017/1/22 16:23
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NeedWechatLogin {
}
