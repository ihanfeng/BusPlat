package com.zhiyin.filter.unity;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Filter统一入口
 */
public class UnityAccessFilter implements Filter {

    private Map<Pattern, Filter> filters = new LinkedHashMap<Pattern, Filter>();

    @Override
    public void init(FilterConfig config) throws ServletException {

        Filter filter1 = new DemoFilter1();
        filter1.init(config);
        filters.put(new Pattern("/*"), filter1);

        DemoFilter2 filter2 = new DemoFilter2();
        filter2.init(config);
        filters.put(new Pattern("/*"), filter2);

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest hsr = (HttpServletRequest) request;
        String path = hsr.getRequestURI().substring(hsr.getContextPath().length());
        GodFilterChain godChain = new GodFilterChain(chain);

        for (Map.Entry<Pattern, Filter> entry : filters.entrySet()) {
            if (entry.getKey().matches(path)) {
                godChain.addFilter(entry.getValue());
            }
        }

        godChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        for (Filter filter : filters.values()) {
            filter.destroy();
        }
    }

}

