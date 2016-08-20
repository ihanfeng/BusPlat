package com.zhiyin.filter.encry;

import com.zhiyin.filter.util.ServletWrapperOutputStream;
import com.zhiyin.filter.util.Util;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.nio.charset.Charset;

public class EncryptServletResponseWrapper extends HttpServletResponseWrapper {

    private final ServletWrapperOutputStream servletWrapperOutputStream;

    public EncryptServletResponseWrapper(
            final HttpServletResponse response,
            final ServletWrapperOutputStream servletWrapperOutputStream) {
        super(response);
        this.servletWrapperOutputStream = servletWrapperOutputStream;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {


//        return new String(servletWrapperOutputStream.toByteArray(), Charset.forName(Util.ENC_UTF8));

        return servletWrapperOutputStream;
    }
}
