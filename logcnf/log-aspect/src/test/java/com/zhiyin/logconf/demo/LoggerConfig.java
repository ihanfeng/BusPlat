package com.zhiyin.logconf.demo;

import com.zhiyin.logconf.ext.automon.AutomonSpringAspectExt;
import com.zhiyin.logconf.aspect.LoggerAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Collections;
import java.util.Set;

@Configuration
@EnableAspectJAutoProxy
public class LoggerConfig {

    private static final boolean SKIP_NULL_FIELDS = true;
    private static final int CROP_THRESHOLD = 7;
    private static final Set<String> EXCLUDE_SECURE_FIELD_NAMES = Collections.<String>emptySet();

//    @Bean
    public LoggerAspect getLoggerBean() {
        LoggerAspect lg = new LoggerAspect();
        return lg;
    }

    @Bean
    public AutomonSpringAspectExt getLoggerBean2() {
        AutomonSpringAspectExt lg = new AutomonSpringAspectExt();
        return lg;
    }
}
