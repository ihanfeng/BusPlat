package com.zhiyin.filter.r2;

import com.zhiyin.filter.HttpHelper;
import com.zhiyin.filter.r2.wrapper.LoggerServletRequestWrapper;
import com.zhiyin.filter.r2.wrapper.LoggerServletResponseWrapper;
import com.zhiyin.filter.r2.wrapper.ServletWrapperOutputStream;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.Locale;

@Slf4j
public class LoggerFilter implements Filter {

    private static final String LOG_BORDER = "\n******************************************";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("log filter ini.");
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

//                if ("POST".equalsIgnoreCase(httpServletRequest.getMethod())) {
        // 防止流读取一次后就没有了, 所以需要将流继续写出去
        ServletRequest requestWrapper =
                new LoggerServletRequestWrapper(Util.convertWithCastCheck(HttpServletRequest.class, request));

        String body = HttpHelper.getBodyString(requestWrapper);

        log.info("req body:{}",body);

        ServletWrapperOutputStream servletOutputStream = new ServletWrapperOutputStream();
        LoggerServletResponseWrapper wrapper =
                new LoggerServletResponseWrapper(
                        Util.convertWithCastCheck(HttpServletResponse.class, response),
                        servletOutputStream);


        chain.doFilter(requestWrapper, wrapper);

        String responseBody = extractResponseBody(servletOutputStream);
        response.getWriter().print(responseBody);

        log.info(createResponseInfoString(responseBody));


    }

    @Override
    public void destroy() {

    }

    /**
     * Returns the response body string between * border.
     *
     * @param response The response whose body will be used to create the response body String.
     * @return String response body between * borders.
     */
    private String createResponseInfoString(final String response) {
        StringBuilder sb = new StringBuilder();
        sb.append(LOG_BORDER).append("\nresponse body: ").append(response).append(LOG_BORDER);
        return sb.toString();
    }

    /**
     * Reads the byte array of the servlet response wrapper and creates a UTF-8 String using its bytes.
     *
     * @param servletWrapperOutputStream The output stream whose byte array will be used to create the string.
     * @return String response body in utf-8 encoding.
     */
    private String extractResponseBody(final ServletWrapperOutputStream servletWrapperOutputStream) {
        return new String(servletWrapperOutputStream.toByteArray(), Charset.forName(Util.ENC_UTF8));
    }


}
