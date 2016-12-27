package com.zhiyin.logger.logback;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.FileAppender;
import org.slf4j.LoggerFactory;

/**
 * Created by wangqinghui on 2016/12/27.
 */
public class LoggerAppenderUtil {

    public static void addAppender(String loggerName){

        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger logger = lc.getLogger(loggerName);
        logger.addAppender( newConsoleAppender(lc) );
        logger.addAppender(newFileAppender(lc));

    }

    public static ConsoleAppender newConsoleAppender(LoggerContext loggerContext) {
        ConsoleAppender consoleAppender = new ConsoleAppender();

        consoleAppender.setContext(loggerContext);
        consoleAppender.setName("gen-console");

        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(loggerContext);
        encoder.setPattern("%r %thread %level - %msg%n");
        encoder.start();

        consoleAppender.setEncoder(encoder);
        consoleAppender.start();
        return consoleAppender;
    }


    public static FileAppender newFileAppender(LoggerContext loggerContext){

        FileAppender fileAppender = new FileAppender();
        fileAppender.setContext(loggerContext);
//        System.out.println("log.base.path" + loggerContext.getProperty("log.base.path"));
        fileAppender.setName("timestamp");
        // set the file name
        fileAppender.setFile("log/genLogger.log");

        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(loggerContext);
        encoder.setPattern("%r %thread %level - %msg%n");
        encoder.start();

        fileAppender.setEncoder(encoder);
        fileAppender.start();

        return fileAppender;
    }

}
