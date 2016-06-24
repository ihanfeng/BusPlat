/*
 * Created by yurtozc on 3/24/15 1:08 PM.
 */

package com.zhiyin.filter.sender.r2.wrapper;



import com.zhiyin.filter.sender.r2.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class LoggerServletRequestWrapper extends HttpServletRequestWrapper {

    private static  final Logger LOGGER = LoggerFactory.getLogger(LoggerServletRequestWrapper.class);

    private String body;

    public LoggerServletRequestWrapper(final HttpServletRequest request) {
        super(request);
        try {
            body = Util.readInputStream(request.getInputStream());
        } catch (IOException ex) {
            LOGGER.warn("error", ex);
        }
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream =
                new ByteArrayInputStream(body.getBytes(Charset.forName(Util.ENC_UTF8)));
        return new ConcreteServletInputstream(byteArrayInputStream);
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream(), Charset.forName(Util.ENC_UTF8)));
    }

    public String getBody() {
        return this.body;
    }


    private static class ConcreteServletInputstream extends ServletInputStream {

        private final ByteArrayInputStream byteArrayInputStream;

        public ConcreteServletInputstream(final ByteArrayInputStream byteArrayInputStream) {
            this.byteArrayInputStream = byteArrayInputStream;
        }

        @Override
        public int read() throws IOException {
            return byteArrayInputStream.read();
        }

        @Override
        public boolean isFinished() {
            return false;
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setReadListener(ReadListener listener) {
        }
    }
}
