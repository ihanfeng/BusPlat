package com.zhiyin.elk.boot.autoconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Configuration
@ConditionalOnProperty(value = "elk.logstash.enabled", matchIfMissing = true)
@EnableConfigurationProperties(LogstashProperties.class)
public class LogstashAutoConfiguration {

    @Configuration
    public class LogstashConfig {

        @Autowired
        private LogstashProperties logstashProperties;

        @Autowired
        private Environment environment;

        @PostConstruct
        public void init() {

            LogAppenderUtil.addAppender();
//            LogAppenderUtil.addFileAppender("aaaaa");

        }

    }
}