package com.zhiyin.filter.module.log;

import com.zhiyin.filter.util.HttpHelper;
import com.zhiyin.filter.util.ServletWrapperOutputStream;
import com.zhiyin.filter.util.Util;
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
        log.info("{} filter ini.", this.getClass().getName());
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        // 防止流读取一次后就没有了, 所以需要将流继续写出去
        LoggerServletRequestWrapper requestWrapper =
                new LoggerServletRequestWrapper(Util.convertWithCastCheck(HttpServletRequest.class, request));

        log.info(createRequestInfoString(httpServletRequest));

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

    public String createRequestInfoString(final HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        Enumeration<String> headerNames = request.getHeaderNames();

        sb.append(LOG_BORDER);
        sb.append("\npath: ").append(request.getServletPath());
        sb.append("\nquery string: ").append(request.getQueryString());
        sb.append("\nmethod: ").append(request.getMethod().toUpperCase(Locale.getDefault()));

        while (headerNames.hasMoreElements()) {

            String headerName = headerNames.nextElement();

            Enumeration<String> headers = request.getHeaders(headerName);

            while (headers.hasMoreElements()) {
                String v = headers.nextElement();
                sb.append("\n").append(headerName).append(": ").append(v);
            }
        }

        if( HttpHelper.isPost(request) ){
            String body = HttpHelper.getBodyString(request);
            sb.append("\nbody: ").append(body);
        }
        sb.append(LOG_BORDER);

        return sb.toString();
    }


}
