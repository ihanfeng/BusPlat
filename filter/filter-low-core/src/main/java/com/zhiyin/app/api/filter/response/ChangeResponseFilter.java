package com.zhiyin.app.api.filter.response;

import com.zhiyin.app.logger.LoggerUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * http://blog.csdn.net/zhangzeyuaaa/article/details/49076095
 *
 * @author hg
 */
public class ChangeResponseFilter implements Filter {

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        MyResponseWrapper responseWrapper = new MyResponseWrapper((HttpServletResponse) response);
        chain.doFilter(request, responseWrapper);
        MyWriter writer = responseWrapper.getMyWriter();
        if (writer != null) {
            String content = writer.getContent();
//			content = content.replace("$replace$", "This is replaced!");
            System.out.println(content);
            LoggerUtil.logRequestAccess(content);
            response.getWriter().write(content);
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

}
