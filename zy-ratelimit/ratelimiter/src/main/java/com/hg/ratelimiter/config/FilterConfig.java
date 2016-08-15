package com.hg.ratelimiter.config;

import com.hg.ratelimiter.filter.RateLimiterFilter;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.Filter;

/**
 * Created by hg on 2016/8/15.
 */
public class FilterConfig {
    @Bean
    public FilterRegistrationBean someFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(someFilter());
        registration.addUrlPatterns("/url/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("someFilter");
        registration.setOrder(1);
        return registration;
    }

    @Bean(name = "someFilter")
    public Filter someFilter() {
        return new RateLimiterFilter();
    }
}
