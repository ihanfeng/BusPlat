package com.alibaba.boot.dubbo;

import com.alibaba.dubbo.config.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.boot.dubbo.endpoint.DubboEndpoint;
import com.alibaba.boot.dubbo.health.DubboHealthIndicator;

@Configuration
@EnableConfigurationProperties(DubboProperties.class)
public class DubboAutoConfiguration {

    @Autowired
    private DubboProperties dubboProperties;

    @Bean
    @ConditionalOnMissingBean
    public ApplicationConfig requestApplicationConfig() {
        return dubboProperties.getApplication();
    }

    @Bean
    @ConditionalOnMissingBean
    public RegistryConfig requestRegistryConfig() {
        return dubboProperties.getRegistry();
    }

    @Bean
    @ConditionalOnMissingBean
    public ProtocolConfig requestProtocolConfig() {
        return dubboProperties.getProtocol();
    }

    @Bean
    @ConfigurationProperties(prefix = "endpoints.dubbo", ignoreUnknownFields = false)
    public DubboEndpoint dubboEndpoint() {
        return new DubboEndpoint();
    }

//    @Bean
//    @ConditionalOnMissingBean
//    public ConsumerConfig requestConsumerConfig(){
//        return dubboProperties.getConsumerConfig();
//    }
//
//    @Bean
//    @ConditionalOnMissingBean
//    public ProviderConfig requestProviderConfig(){
//        return dubboProperties.getProviderConfig();
//    }
//
//    @Bean
//    @ConditionalOnMissingBean
//    public MonitorConfig monitorConfig(){
//        return dubboProperties.getMonitorConfig();
//    }
//
//    @Bean
//    @ConditionalOnMissingBean
//    public ModuleConfig moduleConfig(){
//        return dubboProperties.getModuleConfig();
//    }


    @Bean
    public DubboHealthIndicator dubboHealthIndicator() {
        return new DubboHealthIndicator();
    }

}
