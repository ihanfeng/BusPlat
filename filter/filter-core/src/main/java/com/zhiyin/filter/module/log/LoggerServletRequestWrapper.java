package com.zhiyin.filter.module.log;

import com.zhiyin.filter.util.ServletWrappperInputstream;
import com.zhiyin.filter.util.Util;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

@Slf4j
public class LoggerServletRequestWrapper extends HttpServletRequestWrapper {

    private String body;

    public LoggerServletRequestWrapper(final HttpServletRequest request) {
        super(request);
        try {
            body = Util.readInputStream(request.getInputStream());
        } catch (IOException ex) {
            log.warn("error", ex);
        }
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream =
                new ByteArrayInputStream(body.getBytes(Charset.forName(Util.ENC_UTF8)));
        return new ServletWrappperInputstream(byteArrayInputStream);
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream(), Charset.forName(Util.ENC_UTF8)));
    }

    public String getBody() {
        return this.body;
    }

}