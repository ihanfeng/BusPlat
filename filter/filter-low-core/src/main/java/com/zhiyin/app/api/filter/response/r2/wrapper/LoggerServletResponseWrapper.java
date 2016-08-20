/*
 * Created by yurtozc on 3/24/15 1:08 PM.
 */

package com.zhiyin.app.api.filter.response.r2.wrapper;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

public class LoggerServletResponseWrapper extends HttpServletResponseWrapper {

    private final ServletWrapperOutputStream servletWrapperOutputStream;

    /**
     * Constructs a wrapper object wrapping the given response and servlet output stream in the constructor.
     * If the response data directly used
     *
     * @param response
     * @param servletWrapperOutputStream
     * @see javax.servlet.http.HttpServletResponseWrapper
     */
    public LoggerServletResponseWrapper(
            final HttpServletResponse response,
            final ServletWrapperOutputStream servletWrapperOutputStream) {
        super(response);
        this.servletWrapperOutputStream = servletWrapperOutputStream;
    }

    /**
     * Returns the servlet output stream owned by this wrapper.
     */
    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return servletWrapperOutputStream;
    }
}
