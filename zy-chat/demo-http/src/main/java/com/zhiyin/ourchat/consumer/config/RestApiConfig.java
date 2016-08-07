package com.zhiyin.ourchat.consumer.config;

import com.zhiyin.ourchat.aip.client.ChatRestApiService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by hg on 2016/8/5.
 */
@Configuration
//@ComponentScan("com.zhiyin.ourchat.aip.client")
@ComponentScan(basePackageClasses = {ChatRestApiService.class})
        public class RestApiConfig {
}
