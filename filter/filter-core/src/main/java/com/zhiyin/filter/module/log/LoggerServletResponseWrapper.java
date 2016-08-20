package com.zhiyin.filter.module.log;

import com.zhiyin.filter.util.ServletWrapperOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

public class LoggerServletResponseWrapper extends HttpServletResponseWrapper {

    private final ServletWrapperOutputStream servletWrapperOutputStream;

    public LoggerServletResponseWrapper(
            final HttpServletResponse response,
            final ServletWrapperOutputStream servletWrapperOutputStream) {
        super(response);
        this.servletWrapperOutputStream = servletWrapperOutputStream;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return servletWrapperOutputStream;
    }
}
