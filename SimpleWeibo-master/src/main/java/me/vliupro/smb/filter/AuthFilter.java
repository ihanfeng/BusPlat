package me.vliupro.smb.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by vliupro on 16-5-31.
 */
public class AuthFilter implements Filter {

    protected FilterConfig filterConfig = null;
    private Set<String> notCheckUrls = new HashSet<String>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        String notCheckUrlsStr = this.filterConfig.getInitParameter("notCheckUrls");
        if (notCheckUrlsStr != null) {
            String[] params = notCheckUrlsStr.split(",");
            for (int i = 0 ; i < params.length ; i ++) {
                notCheckUrls.add(params[i].trim());
            }
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        if (checkRequestURIIntNotFilterList(request)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            if (session.getAttribute("user") == null) {
                response.sendRedirect(request.getContextPath() + "/index?begin=1&total=10");
            } else {
                response.sendRedirect(request.getContextPath() + "/loginIndex?begin=1&total=10");
            }
        }
    }

    @Override
    public void destroy() {
        this.notCheckUrls.clear();
    }

    private boolean checkRequestURIIntNotFilterList(HttpServletRequest request) {
        String uri = request.getServletPath()
                + (request.getPathInfo() == null ? "" : request.getPathInfo());
//        System.out.println("是否包括："+uri+"; "+notCheckUrls+" == "+notCheckUrls.contains(uri));
        return this.notCheckUrls.contains(uri);
    }
}
