package com.vcg.micro.user.support.dubbo.service;

import com.alibaba.dubbo.config.ServiceConfig;
import com.alibaba.dubbo.config.spring.AnnotationBean;
import com.alibaba.dubbo.config.spring.ServiceBean;
import com.vcg.micro.user.service.UserService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Set;

/**
 * Created by wuyu on 2016/7/5.
 * <p>
 * 导出服务
 */
@Configuration
@Component
public class DubboServiceExport {


    @Bean
    public ServiceBean<UserService> userServiceBean(UserService userService) {
        return export(userService, UserService.class);
    }


    private AnnotationBean annotationBean;

    @Bean
    public AnnotationBean annotationBean() {
        annotationBean = new AnnotationBean();
        return annotationBean;
    }

    public <T> ServiceBean<T> export(T t, Class<T> iFace) {
        ServiceBean<T> serviceBean = new ServiceBean<>();
        serviceBean.setRef(t);
        serviceBean.setInterface(iFace);
        registerServiceBean(serviceBean);
        return serviceBean;
    }

    public <T> void registerServiceBean(ServiceBean<T> t) {
        if (annotationBean != null) {
            Field serviceConfigField = ReflectionUtils.findField(AnnotationBean.class, "serviceConfigs");
            serviceConfigField.setAccessible(true);
            Set<ServiceConfig<?>> serviceConfig = (Set<ServiceConfig<?>>) ReflectionUtils.getField(serviceConfigField, annotationBean);
            serviceConfig.add(t);
        }
    }
}
