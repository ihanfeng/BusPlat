package com.zhiyin.fms.config;

import com.zhiyin.filter.parm.AddParameterByNewMapFilter;
import com.zhiyin.filter.r2.LoggerFilter;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hg on 2016/6/23.
 */
@Configuration
public class FilterConfig    {

    @Bean
    public FilterRegistrationBean addParm(){
        AddParameterByNewMapFilter demoFilter=new AddParameterByNewMapFilter();
        FilterRegistrationBean registrationBean=new FilterRegistrationBean();
        registrationBean.setFilter(demoFilter);
        List<String> urlPatterns=new ArrayList<String>();
        urlPatterns.add("/*");//拦截路径，可以添加多个
        registrationBean.setUrlPatterns(urlPatterns);
        registrationBean.setOrder(10001);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean getDemoFilter(){
        LoggerFilter demoFilter=new LoggerFilter();
        FilterRegistrationBean registrationBean=new FilterRegistrationBean();
        registrationBean.setFilter(demoFilter);
        List<String> urlPatterns=new ArrayList<String>();
        urlPatterns.add("/*");//拦截路径，可以添加多个
        registrationBean.setUrlPatterns(urlPatterns);
        registrationBean.setOrder(1000);
        return registrationBean;
    }
//    @Bean
//    public ServletRegistrationBean getDemoServlet(){
//        LoggerFilter demoServlet=new LoggerFilter();
//        ServletRegistrationBean registrationBean=new ServletRegistrationBean();
//        registrationBean.setServlet(demoServlet);
//        List<String> urlMappings=new ArrayList<String>();
//        urlMappings.add("/demoservlet");////访问，可以添加多个
//        registrationBean.setUrlMappings(urlMappings);
//        registrationBean.setLoadOnStartup(1);
//        return registrationBean;
//    }
//    @Bean
//    public ServletListenerRegistrationBean<EventListener> getDemoListener(){
//        ServletListenerRegistrationBean<EventListener> registrationBean
//                =new ServletListenerRegistrationBean<>();
//        registrationBean.setListener(new DemoListener());
////		registrationBean.setOrder(1);
//        return registrationBean;
//    }
}