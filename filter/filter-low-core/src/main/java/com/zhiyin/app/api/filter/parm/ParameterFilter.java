package com.zhiyin.app.api.filter.parm;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * Servlet Filter implementation class CommonFilter
 */
public class ParameterFilter implements Filter {

    /**
     * Default constructor.
     */
    public ParameterFilter() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        // TODO Auto-generated method stub
        // place your code here
        HttpServletRequest hr = (HttpServletRequest) request;
        String url = hr.getRequestURL().toString();
        String uri = hr.getRequestURI();
        // syso
        // BufferedReader reader = request.getReader();

        String se = hr.getServletPath().trim();
        System.out.println("url:" + url + ";uri:" + uri);
        if (url.equals("xxx")) {// 不需要过滤的url，这里可以使用一个配置文件配置这些url，项目启动时读入内存一个map中，然后在这里进行判断
            // 我定义的是urlFilterMap，然后在这里urlFilterMap.containsValue(url)进行判断
            chain.doFilter(hr, response);
        } else {
            ParameterRequestWrapper requestWrapper = new ParameterRequestWrapper(
                    (HttpServletRequest) request);
            requestWrapper.addParameter("accesstoken", "sss");

            for (Map.Entry<String, String[]> entry : requestWrapper
                    .getParameterMap().entrySet()) {

                System.out.println("Key = " + entry.getKey() + ", Value = "
                        + entry.getValue()[0]);

            }

            chain.doFilter(requestWrapper, response);
        }
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

}
