package com.zhiyin.elk.boot.autoconfig;

import java.net.InetSocketAddress;

import javax.annotation.PostConstruct;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import net.logstash.logback.appender.LogstashTcpSocketAppender;
import net.logstash.logback.encoder.LogstashEncoder;

/**
 * 该类是可以配置成xml配置文件的，但是那样的话，就不能由客户端指定参数了
 */
@Component
public class LogstashConfig {

    @Autowired
    private LogstashProperties logstashProperties;

    @Autowired
    private Environment environment;

    @PostConstruct
    public void init() {
        Logger rootLogger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        LogstashTcpSocketAppender appender = new LogstashTcpSocketAppender();
        appender.setName("stash");
        appender.addDestinations(new InetSocketAddress(logstashProperties.getHost(), logstashProperties.getPort()));
       
        LogstashEncoder encoder = new LogstashEncoder();
        encoder.setCustomFields("{ \"service\":\"" + environment.getProperty("spring.application.name") + "\"}");//服务名会在日志中显示（可以方便的知道该日志是哪个服务的）
        encoder.start();
        
        appender.setEncoder(encoder);
        appender.setContext(rootLogger.getLoggerContext());
        appender.start();
        rootLogger.addAppender(appender);
        rootLogger.setLevel(Level.toLevel(logstashProperties.getLevel()));
    }
}