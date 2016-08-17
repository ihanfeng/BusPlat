package com.zhiyin.filter.encry;

//(C) 1998-2015 Information Desire Software GmbH
//www.infodesire.com

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * 解密过滤器
 *
 * @author eugen
 */
public class DecryptRequestFilter implements Filter {

    private static final Logger log = LoggerFactory
            .getLogger(DecryptRequestFilter.class);

    private static final Logger accessLogger = LoggerFactory
            .getLogger("appapi.access");

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
     * javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        MDC.put("request_id", UUID.randomUUID().toString().replace("-", ""));

        String uri = ((HttpServletRequest) request).getRequestURI();

        // 对于解密数据错误，会重定向错误处理URL，跳过解密。
        if( uri.startsWith("/appapi/error/")){
            NoneDecryptRequestDataWrapper myRequestWrapper = null;
            try {
                myRequestWrapper = new NoneDecryptRequestDataWrapper(
                        (HttpServletRequest) request);
            } catch (Exception e) {
                log.error("should not happen, e:",e);
            }
            chain.doFilter(myRequestWrapper, response);
            return;
        }


        if (request instanceof HttpServletRequest) {
            // 判断客户端是否加密
            String entry = ((HttpServletRequest) request).getHeader("entry");
            try {
                if ("rsa".equals(entry)) {
                    log.info("entry ras");
                    RasDecryptRequestDataWrapper myRequestWrapper = new RasDecryptRequestDataWrapper(
                            (HttpServletRequest) request);
                    chain.doFilter(myRequestWrapper, response);
                } else if ("aes".equals(entry)) {
                    log.info("entry aes");
                    AesDecryptRequestDataWrapper myRequestWrapper = new AesDecryptRequestDataWrapper(
                            (HttpServletRequest) request);
                    chain.doFilter(myRequestWrapper, response);
                } else {
                    log.info("entry none");
                    NoneDecryptRequestDataWrapper myRequestWrapper = new NoneDecryptRequestDataWrapper(
                            (HttpServletRequest) request);
                    chain.doFilter(myRequestWrapper, response);
                }

            } catch (Exception e) {
                String redir = "/appapi/error/encrypt400";
                log.error("decry data error, redirect to:{}, e:",redir,e);
                ((HttpServletResponse)response).sendRedirect( redir);
                return;
            }

        } else {

            // non http request, can not create wrapper

        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub

    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }


}