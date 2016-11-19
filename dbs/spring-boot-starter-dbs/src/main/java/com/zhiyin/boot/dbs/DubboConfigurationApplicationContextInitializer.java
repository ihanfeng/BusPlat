package com.zhiyin.boot.dbs;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class DubboConfigurationApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
//        this.applicationContext = applicationContext;
//        Environment env = applicationContext.getEnvironment();
//        String scan = env.getProperty("spring.dubbo.scan");
//        if (scan != null) {
//            AnnotationBean scanner = (AnnotationBean) registerAndInstance(scan);
//            scanner.setPackage(scan);
//            scanner.setApplicationContext(applicationContext);
//            applicationContext.addBeanFactoryPostProcessor(scanner);
//        }
    }

}
