package com.zhiyin.boot.dubbo;

import com.alibaba.dubbo.config.spring.AnnotationBean;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

public class DubboConfigurationApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        Environment env = applicationContext.getEnvironment();
        String scan = env.getProperty("dubbo.scan");
        if (scan != null) {
            AnnotationBean scanner = (AnnotationBean) registerAndInstance(scan);
            scanner.setPackage(scan);
            scanner.setApplicationContext(applicationContext);
            applicationContext.addBeanFactoryPostProcessor(scanner);
        }
    }

    private Object registerAndInstance(String scan) {
        if (!applicationContext.containsBean(AnnotationBean.class.getName())) {
            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(AnnotationBean.class);
            beanDefinitionBuilder.addPropertyValue("package", scan);
            beanDefinitionBuilder.addPropertyValue("applicationContext", applicationContext);
            BeanDefinitionRegistry beanDefinitonRegistry = (BeanDefinitionRegistry) applicationContext.getBeanFactory();
            beanDefinitonRegistry.registerBeanDefinition(AnnotationBean.class.getName(),
                    beanDefinitionBuilder.getRawBeanDefinition());
        }
        return BeanUtils.instantiate(AnnotationBean.class);

    }

}
