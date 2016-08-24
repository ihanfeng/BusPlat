package com.zhiyin.consu.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan
@PropertySource("classpath:config/dubbo-consumer-config.properties")
@ImportResource("classpath:applicationContext-dubbo-consumer.xml")
public class DubboConsumerConfig {

    @Value("${dubbo.application.name}")
    private String applicationName;

    @Value("${dubbo.application.owner}")
    private String applicationOwner;

    @Value("${dubbo.registry.address}")
    private String registryAddress;

    @Value("${dubbo.provider.version}")
    private String providerVersion;

    @Value("${dubbo.comsumer.version}")
    private String comsumerVersion;

    @Value("${dubbo.annotation.package}")
    private String annotationPackage;


//    @Resource
//    private Environment environment;
//
//    @Bean
//    public ApplicationConfig applicationConfig() {
//
//        System.out.println(applicationName);
//
//        ApplicationConfig applicationConfig = new ApplicationConfig();
//        applicationConfig.setName( applicationName );
//        applicationConfig.setLogger("slf4j");
//        return applicationConfig;
//    }
//
//    @Bean
//    public RegistryConfig registryConfig() {
//        RegistryConfig registryConfig = new RegistryConfig();
//        registryConfig.setAddress( registryAddress );
//        return registryConfig;
//    }
//
//    @Bean
//    public AnnotationBean annotationBean() {
//        AnnotationBean annotationBean = new AnnotationBean();
//        annotationBean.setPackage( annotationPackage );
//        return annotationBean;
//    }
//
//    @Bean
//    public ConsumerConfig consumer(){
//        ConsumerConfig consumerConfig = new ConsumerConfig();
//        consumerConfig.setVersion( comsumerVersion );
//        consumerConfig.setCheck(false);
//        return consumerConfig;
//    }
}