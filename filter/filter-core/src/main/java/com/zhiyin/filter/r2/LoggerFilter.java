/*
 * Created by yurtozc on 3/24/15 12:58 PM.
 * 
 * https://github.com/cyurtoz/loggerfilter
 */

package com.zhiyin.filter.r2;


import com.zhiyin.filter.HttpHelper;
import com.zhiyin.filter.r2.wrapper.LoggerServletResponseWrapper;
import com.zhiyin.filter.r2.wrapper.ServletWrapperOutputStream;
import com.zhiyin.filter.AuthenticationRequestWrapper;
import com.zhiyin.filter.r2.wrapper.LoggerServletRequestWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.Locale;

/**
 *  This filter provides request and response logging.
 *  The request path, query string, request method, all request headers, request body and the response body are logged.
 *  To prevent request and response streams to be exhausted, request and response wrappers are created and
 *  copied data of them are logged instead of the actual data.
 *  To activate the logging, a sample filter declaration in web.xml which logs requests to all paths is
 *  as follows:
 *  <pre>
 * {@code
 *  <filter>
 *      <filter-name>LoggerInterceptor</filter-name>
 *      <filter-class>com.cagatay.filter.LoggerFilter</filter-class>
 *  </filter>
 *  <filter-mapping>
 *      <filter-name>LoggerInterceptor</filter-name>
 *      <url-pattern>/*</url-pattern>
 *  </filter-mapping>
 * }
 * </pre>
 */
public class LoggerFilter implements Filter {

    private static final String LOG_BORDER = "\n******************************************";

    public String createRequestInfoString(final LoggerServletRequestWrapper request) {
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

        sb.append("\nbody: ").append(request.getBody());
        sb.append(LOG_BORDER);

        return sb.toString();
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {
//        LoggerServletRequestWrapper customHttpServletRequestWrapper =
//                new LoggerServletRequestWrapper(Util.convertWithCastCheck(HttpServletRequest.class, request));

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        ServletRequest requestWrapper = new AuthenticationRequestWrapper(httpServletRequest);
            String body = HttpHelper.getBodyString(requestWrapper);
        System.out.println( body );
//        if ("POST".equalsIgnoreCase(httpServletRequest.getMethod())) {
//            // 防止流读取一次后就没有了, 所以需要将流继续写出去
//            ServletRequest requestWrapper = new AuthenticationRequestWrapper(httpServletRequest);
//            String body = HttpHelper.getBodyString(requestWrapper);
//            System.out.println( body );
//        }

        ServletWrapperOutputStream servletOutputStream = new ServletWrapperOutputStream();
        LoggerServletResponseWrapper wrapper =
                new LoggerServletResponseWrapper(
                        Util.convertWithCastCheck(HttpServletResponse.class, response),
                        servletOutputStream);

//        System.out.println(createRequestInfoString(customHttpServletRequestWrapper));

        chain.doFilter(requestWrapper, wrapper);

        String responseBody = extractResponseBody(servletOutputStream);
        response.getWriter().print(responseBody);

//        LoggerUtil.logRequestAccess("response body:"+responseBody);
        System.out.println(createResponseInfoString(responseBody));
//        LOGGER.trace(createResponseInfoString(responseBody));
    }

    @Override
    public void destroy() {

    }

    /**
     * Returns the response body string between * border.
     *
     * @param response The response whose body will be used to create the response body String.
     * @return  String response body between * borders.
     *
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
