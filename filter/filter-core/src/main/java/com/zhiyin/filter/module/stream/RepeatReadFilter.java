package com.zhiyin.filter.module.stream;

import com.zhiyin.filter.util.Util;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

/**
 * POST可重复读的请求过滤
 * Created by hg on 2016/8/19.
 */
@Slf4j
public class RepeatReadFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("{} filter ini.", this.getClass().getName());
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        // 防止流读取一次后就没有了, 所以需要将流继续写出去
        if ("POST".equalsIgnoreCase(httpServletRequest.getMethod())) {
            HttpServletRequestWrapper requestWrapper =
                    new RepeatReadRequestWrapper(Util.convertWithCastCheck(HttpServletRequest.class, request));
            chain.doFilter(requestWrapper, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }

}
