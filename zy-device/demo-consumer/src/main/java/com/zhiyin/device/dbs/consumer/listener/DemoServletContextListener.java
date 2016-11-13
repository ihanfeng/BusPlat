package com.zhiyin.device.dbs.consumer.listener;

import java.io.File;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.netflix.config.ConfigurationManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//@WebListener
public class DemoServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("starting server");

        // It will create new thread
        Timer timer=new Timer();
        timer.schedule(new MyTimerTask(timer),new Date(), 5000);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("stopping server");
    }


    public static void loadConfig(String config) {

        String configTrim = Optional.fromNullable(config).or("").trim();
        if(Strings.isNullOrEmpty(configTrim)){
            return;
        }

        String[] infos = configTrim.split(";");
        if(infos == null || infos.length == 0){
            return ;
        }

        for(String tmp : infos){

            if(Strings.isNullOrEmpty(tmp)){
                continue;
            }

            String[] keyVal = tmp.split("=");
            if(keyVal == null || keyVal.length!=2){
                continue;
            }

            ConfigurationManager.getConfigInstance().setProperty( keyVal[0] , keyVal[1]);
        }

    }


}


class MyTimerTask extends TimerTask {

    Timer timer;
    public MyTimerTask(Timer timer) {
        this.timer=timer;
    }

    @Override
    public void run() {
        System.out.println("Executing timer task");
    }

}
