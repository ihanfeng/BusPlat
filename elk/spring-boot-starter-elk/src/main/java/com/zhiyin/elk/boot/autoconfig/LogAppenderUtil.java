package com.zhiyin.elk.boot.autoconfig;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.FileAppender;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import net.logstash.logback.appender.LogstashTcpSocketAppender;
import net.logstash.logback.encoder.LogstashEncoder;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangqinghui on 2016/12/27.
 */
public class LogAppenderUtil {

    public static void addAppender() {
        Logger rootLogger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        rootLogger.addAppender( newConsoleAppender( rootLogger.getLoggerContext() ) );
    }
    public static void addFileAppender(String name){
        Map<String,String> map = new HashMap<String, String>();
        map.put("log-source","system-gen");
        addFileAppender(name,"./"+name+".json", JSON.toJSONString(map) );
    }
    public static void addFileAppender(String name,String filePath,String customFields) {

        Logger rootLogger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        FileAppender appender = new FileAppender();
        appender.setName(name);
        appender.setFile( filePath );

        appender.addFilter( newFileter() );

        LogstashEncoder encoder = new LogstashEncoder();
        encoder.setIncludeCallerData(true);
        encoder.setCustomFields( customFields );//服务名会在日志中显示（可以方便的知道该日志是哪个服务的）
        encoder.start();

        appender.setEncoder(encoder);
        appender.setContext(rootLogger.getLoggerContext());
        appender.start();
        rootLogger.addAppender(appender);
//        rootLogger.setLevel(Level.toLevel(logstashProperties.getLevel()));
    }



    public static ConsoleAppender newConsoleAppender(LoggerContext loggerContext) {
        ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setName("gen-console");
        consoleAppender.setContext(loggerContext);

        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(loggerContext);
        encoder.setPattern("%r %thread %level - %msg%n");
        encoder.start();

        consoleAppender.addFilter( newFileter() );
        consoleAppender.setEncoder(encoder);
        consoleAppender.start();
        return consoleAppender;
    }



    public void logstash(LogstashProperties logstashProperties, Environment environment) {
        Logger rootLogger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        LogstashTcpSocketAppender appender = new LogstashTcpSocketAppender();
        appender.setName("boot-stash");
        appender.addDestinations(new InetSocketAddress(logstashProperties.getHost(), logstashProperties.getPort()));

        //添加日志功率，只有warn和error才记录到elk stack中
        appender.addFilter(new Filter<ILoggingEvent>() {
            @Override
            public FilterReply decide(ILoggingEvent event) {
                if(event.getLevel() == Level.INFO || event.getLevel() == Level.ERROR || event.getLevel()==Level.WARN){
                    return FilterReply.ACCEPT;
                }
                return FilterReply.DENY;
            }
        });

        LogstashEncoder encoder = new LogstashEncoder();
        encoder.setIncludeCallerData(true);
        encoder.setCustomFields("{ \"service\":\"" + environment.getProperty("spring.application.name") + "\"}");//服务名会在日志中显示（可以方便的知道该日志是哪个服务的）
        encoder.start();

        appender.setEncoder(encoder);
        appender.setContext(rootLogger.getLoggerContext());
        appender.start();
        rootLogger.addAppender(appender);
        rootLogger.setLevel(Level.toLevel(logstashProperties.getLevel()));
    }


    public static Filter<ILoggingEvent> newFileter(){

        return new Filter<ILoggingEvent>() {
            @Override
            public FilterReply decide(ILoggingEvent event) {

                if (event.getLevel() == Level.DEBUG) {
                    return FilterReply.DENY;
                }

//                System.out.println(Thread.currentThread().getName());
                String str = event.getMDCPropertyMap().get("traceId");
                if (Strings.isNullOrEmpty(str)) {
                    return FilterReply.DENY;
                }

                return FilterReply.ACCEPT;
            }
        };
    }

}
