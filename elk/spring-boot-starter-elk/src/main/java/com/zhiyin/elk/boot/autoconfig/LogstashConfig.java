package com.zhiyin.elk.boot.autoconfig;

import java.net.InetSocketAddress;

import javax.annotation.PostConstruct;

import ch.qos.logback.classic.AsyncAppender;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.appender.LogstashSocketAppender;
import net.logstash.logback.stacktrace.ShortenedThrowableConverter;
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
@Slf4j
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

        //添加日志功率，只有warn和error才记录到elk stack中
        appender.addFilter(new Filter<ILoggingEvent>() {
            @Override
            public FilterReply decide(ILoggingEvent event) {
//                event.getMDCPropertyMap()
                if(event.getLevel() == Level.ERROR || event.getLevel()==Level.WARN){
                    return FilterReply.ACCEPT;
                }
                return FilterReply.DENY;
            }
        });
       
        LogstashEncoder encoder = new LogstashEncoder();
        encoder.setCustomFields("{ \"service\":\"" + environment.getProperty("spring.application.name") + "\"}");//服务名会在日志中显示（可以方便的知道该日志是哪个服务的）
        encoder.start();
        
        appender.setEncoder(encoder);
        appender.setContext(rootLogger.getLoggerContext());
        appender.start();
        rootLogger.addAppender(appender);
        rootLogger.setLevel(Level.toLevel(logstashProperties.getLevel()));
    }

    /*
    @PostConstruct
    private void init() {
        if (myPro.getLogging().getLogstash().isEnabled()) {
            addLogstashAppender();
        }
    }

    public void addLogstashAppender() {
        log.info("Initializing Logstash logging");

        LogstashSocketAppender logstashAppender = new LogstashSocketAppender();
        logstashAppender.setName("LOGSTASH");
        logstashAppender.setContext(context);
        String customFields = "{\"app_name\":\"" + appName + "\",\"app_port\":\"" + serverPort + "\"}";

        // Set the Logstash appender config from JHipster properties
        logstashAppender.setSyslogHost(myPro.getLogging().getLogstash().getHost());
        logstashAppender.setPort(myPro.getLogging().getLogstash().getPort());
        logstashAppender.setCustomFields(customFields);

        //添加日志功率，只有warn和error才记录到elk stack中
        logstashAppender.addFilter(new Filter<ILoggingEvent>() {
            @Override
            public FilterReply decide(ILoggingEvent event) {
                if(event.getLevel() == Level.ERROR || event.getLevel()==Level.WARN){
                    return FilterReply.ACCEPT;
                }
                return FilterReply.DENY;
            }
        });
        // Limit the maximum length of the forwarded stacktrace so that it won't exceed the 8KB UDP limit of logstash
        ShortenedThrowableConverter throwableConverter = new ShortenedThrowableConverter();
        throwableConverter.setMaxLength(7500);
        throwableConverter.setRootCauseFirst(true);
        logstashAppender.setThrowableConverter(throwableConverter);

        logstashAppender.start();

        // Wrap the appender in an Async appender for performance
        AsyncAppender asyncLogstashAppender = new AsyncAppender();
        asyncLogstashAppender.setContext(context);
        asyncLogstashAppender.setName("ASYNC_LOGSTASH");
        asyncLogstashAppender.setQueueSize(myPro.getLogging().getLogstash().getQueueSize());
        asyncLogstashAppender.addAppender(logstashAppender);
        asyncLogstashAppender.start();

        context.getLogger("ROOT").addAppender(asyncLogstashAppender);
    }
    */

}