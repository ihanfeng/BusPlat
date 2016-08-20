package com.zhiyin.filter.encry;

import com.wustrive.aesrsa.util.AES;
import com.zhiyin.filter.config.FilterInfoConfig;
import com.zhiyin.filter.config.SecurityKeyConfig;
import com.zhiyin.filter.module.log.LoggerServletResponseWrapper;
import com.zhiyin.filter.util.HttpHelper;
import com.zhiyin.filter.util.ServletWrapperOutputStream;
import com.zhiyin.filter.util.Util;
import lombok.extern.slf4j.Slf4j;
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
 */
@Slf4j
public class DecryptRequestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        MDC.put("request_id", UUID.randomUUID().toString().replace("-", ""));

        String uri = ((HttpServletRequest) request).getRequestURI();

        // 对于解密数据错误，会重定向错误处理URL，跳过解密。
        if (uri.startsWith("/appapi/error/")) {
            NoneDecryptRequestDataWrapper myRequestWrapper = null;
            try {
                myRequestWrapper = new NoneDecryptRequestDataWrapper(
                        (HttpServletRequest) request);
            } catch (Exception e) {
                log.error("should not happen, e:", e);
            }
            chain.doFilter(myRequestWrapper, response);
            return;
        }


        if (request instanceof HttpServletRequest) {
            // 判断客户端是否加密
            String entry = ((HttpServletRequest) request).getHeader(FilterInfoConfig.EncryptTypeHeaderName );
            try {
                if ("rsa".equals(entry)) {
                    log.info("entry ras");
                    RasDecryptRequestDataWrapper myRequestWrapper = new RasDecryptRequestDataWrapper(
                            (HttpServletRequest) request);
                    chain.doFilter(myRequestWrapper, response);
                } else if ("aes".equals(entry)) {
                    log.info("entry by aes");
                    AesDecryptRequestDataWrapper requestWrapper = new AesDecryptRequestDataWrapper(
                            (HttpServletRequest) request);

                    ServletWrapperOutputStream servletOutputStream = new ServletWrapperOutputStream();
                    EncryptServletResponseWrapper wrapper =
                            new EncryptServletResponseWrapper(
                                    Util.convertWithCastCheck(HttpServletResponse.class, response),
                                    servletOutputStream);


                    chain.doFilter(requestWrapper, wrapper);

                    String responseBody = HttpHelper.extractResponseBody(servletOutputStream);

                    String encryptBody = AES.encryptToBase64(responseBody, SecurityKeyConfig.AesKey);

                    response.getWriter().print(encryptBody);

                } else {
                    log.info("entry none");
//                    NoneDecryptRequestDataWrapper myRequestWrapper = new NoneDecryptRequestDataWrapper(
//                            (HttpServletRequest) request);
                    chain.doFilter(request, response);
                }

            } catch (Exception e) {
                String redir = "/appapi/error/encrypt400";
                log.error("decry data error, redirect to:{}, e:", redir, e);
                ((HttpServletResponse) response).sendRedirect(redir);
                return;
            }

        }else{
            chain.doFilter(request,response);
        }


    }


    @Override
    public void destroy() {
    }


}