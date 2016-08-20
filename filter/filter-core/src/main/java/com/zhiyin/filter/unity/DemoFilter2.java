package com.zhiyin.filter.unity;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by wangqinghui on 2016/8/18.
 */
@Slf4j
public class DemoFilter2 implements NamedFilter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        log.info("demo filter 2.");

    }

    @Override
    public void destroy() {

    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }
}
