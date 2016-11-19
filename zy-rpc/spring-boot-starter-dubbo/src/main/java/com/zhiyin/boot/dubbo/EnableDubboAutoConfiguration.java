package com.zhiyin.boot.dubbo;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Created by hg on 2016/10/31.
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(value = { DubboAutoConfiguration.class })
public @interface EnableDubboAutoConfiguration {

}