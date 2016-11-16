package com.zhiyin.boot.dbs;

import com.zhiyin.boot.dbs.health.DruidHealthIndicator;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.google.common.base.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(DruidConfigProperties.class)
public class DruidAutoConfiguration {

    @Autowired
    private DruidConfigProperties druidConfigProperties;

    @Bean
    @ConditionalOnMissingBean
    public ServletRegistrationBean druidServlet() {
//        System.out.println("durid.");
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addUrlMappings(Optional.fromNullable(druidConfigProperties.getUrlMappings()).or("/druid/*"));
        //reg.addInitParameter("allow", "127.0.0.1");// IP白名单 (没有配置或者为空，则允许所有访问)
        //reg.addInitParameter("deny","");// IP黑名单 (存在共同时，deny优先于allow)
        reg.addInitParameter("loginUsername", Optional.fromNullable(druidConfigProperties.getLoginUsername()).or("zy"));
        reg.addInitParameter("loginPassword", Optional.fromNullable(druidConfigProperties.getLoginPassword()).or("zy"));
        reg.addInitParameter("restEnable", "false");// 禁用HTML页面上的“Reset All”功能
        return reg;
    }

    @Bean
    @ConditionalOnMissingBean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

//    @Bean
//    @ConfigurationProperties(prefix = "endpoints.dubbo", ignoreUnknownFields = false)
//    public DubboEndpoint dubboEndpoint() {
//        return new DubboEndpoint();
//    }

    @Bean
    public DruidHealthIndicator dubboHealthIndicator() {
        return new DruidHealthIndicator();
    }

}
