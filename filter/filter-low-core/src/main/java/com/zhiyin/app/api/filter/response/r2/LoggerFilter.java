/*
 * Created by yurtozc on 3/24/15 12:58 PM.
 * 
 * https://github.com/cyurtoz/loggerfilter
 */

package com.zhiyin.app.api.filter.response.r2;

import com.zhiyin.app.api.filter.response.r2.wrapper.LoggerServletRequestWrapper;
import com.zhiyin.app.api.filter.response.r2.wrapper.LoggerServletResponseWrapper;
import com.zhiyin.app.api.filter.response.r2.wrapper.ServletWrapperOutputStream;
import com.zhiyin.app.logger.LoggerUtil;
import org.apache.log4j.Logger;

import javax.servlet.*;
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

    private static final Logger LOGGER = Logger.getLogger(LoggerFilter.class);
    private static final String LOG_BORDER = "\n******************************************";


    /**
     * Reads the request path, query, method, headers, body and returns all within a formatted String.
     * The request is not the actual request, but the wrapped copy of it.
     *
     * @param request Servlet request wrapper that contains the data of the original servlet.
     * @return Info string contains request path, query, method, headers, body within * borders.
     */

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

    /**
     * Main behaviour of the filter; reads the servlet request, prints request info and body,
     * delegates the request to the filter chain, and prints the response body.
     *
     * @param request A request that was delegated to this filter.
     * @param response A response that was delegated to this filter.
     * @param chain Servlet filter chain which delegates request to the next filter.
     * @see javax.servlet.FilterChain
     * @see javax.servlet.ServletRequest
     * @see javax.servlet.ServletResponse
     */
    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {
//        LoggerServletRequestWrapper customHttpServletRequestWrapper =
//                new LoggerServletRequestWrapper(Util.convertWithCastCheck(HttpServletRequest.class, request));
        ServletWrapperOutputStream servletOutputStream = new ServletWrapperOutputStream();
        LoggerServletResponseWrapper wrapper =
                new LoggerServletResponseWrapper(
                        Util.convertWithCastCheck(HttpServletResponse.class, response),
                        servletOutputStream);

//        LOGGER.trace(createRequestInfoString(customHttpServletRequestWrapper));

        chain.doFilter(request, wrapper);

        String responseBody = extractResponseBody(servletOutputStream);
        response.getWriter().print(responseBody);

        LoggerUtil.logRequestAccess("response body:"+responseBody);
//        System.out.println(createResponseInfoString(responseBody));
//        LOGGER.trace(createResponseInfoString(responseBody));
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

    @Override
    public void destroy() {

    }
}
