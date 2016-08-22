package com.zhiyin.filter.unity;

import javax.servlet.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GodFilterChain implements FilterChain {

    private FilterChain chain;
    private List<Filter> filters = new ArrayList<Filter>();
    private Iterator<Filter> iterator;

    public GodFilterChain(FilterChain chain) {
        this.chain = chain;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response) throws IOException, ServletException {
        if (iterator == null) {
            iterator = filters.iterator();
        }

//        while (iterator.hasNext()) {
//            iterator.next().doFilter(request, response, this);
//        }
//        chain.doFilter(request, response);

        if (iterator.hasNext()) {
            iterator.next().doFilter(request, response, this);
        } else {
            chain.doFilter(request, response);
        }
    }

    public void addFilter(Filter filter) {
        if (iterator != null) {
            throw new IllegalStateException();
        }

        filters.add(filter);
    }

}