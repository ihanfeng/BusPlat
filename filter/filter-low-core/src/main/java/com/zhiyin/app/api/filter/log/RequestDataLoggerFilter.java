package com.zhiyin.app.api.filter.log;

//(C) 1998-2015 Information Desire Software GmbH
//www.infodesire.com

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

/**
 * filter which logs
 *
 * @author eugen
 */
public class RequestDataLoggerFilter implements Filter {

    private static final Logger log = Logger
            .getLogger(RequestDataLoggerFilter.class.getName());

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
     * javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        if (request instanceof HttpServletRequest) {

            RequestDataWrapper myRequestWrapper = new RequestDataWrapper(
                    (HttpServletRequest) request);

            String requestData = myRequestWrapper.getRequestData();
            String clientIP = myRequestWrapper.getRemoteHost();
            String method = myRequestWrapper.getMethod();
            String uri = myRequestWrapper.getRequestURI();

            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat(
                    "dd-MMM-yyyy HH:mm:ss");
            String dateString = format.format(date);

            String message = "= " + dateString + " ===================\n";
            message += "client: " + clientIP + "\n";
            message += "url: " + method + " " + uri + "\n";
            message += "message:\n";
            message += requestData + "\n";
            message += "==========================================\n";

            System.out.println(message);
            // log.finer( message );

            chain.doFilter(myRequestWrapper, response);
        } else {

            // non http request, can not create wrapper

        }

    }

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {
        // nothing to do
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // nothing to do
    }

}