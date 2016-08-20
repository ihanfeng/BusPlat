/*
 * Created by yurtozc on 3/24/15 1:08 PM.
 */

package com.zhiyin.app.api.filter.response.r2.wrapper;

import com.zhiyin.app.api.filter.response.r2.Util;
import org.apache.log4j.Logger;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class LoggerServletRequestWrapper extends HttpServletRequestWrapper {

    private static final Logger LOGGER = Logger.getLogger(LoggerServletRequestWrapper.class);
    private String body;

    /**
     * Initializes a request wrapper and overrides request input stream methods.
     * The constructor reads the request body assigns to the body field.
     * The body can be received via getBody() method.
     * If IOException is thrown, it is caught and logged.
     *
     * @param request
     */
    public LoggerServletRequestWrapper(final HttpServletRequest request) {
        super(request);
        try {
            body = Util.readInputStream(request.getInputStream());
        } catch (IOException ex) {
            LOGGER.warn(ex, ex);
        }
    }

    /**
     * Converts the body String bytes to a utf-8 byte array stream and returns
     * it in a wrapper which has a type of ConcreteServletInputstream
     *
     * @return ServletInputStream data of this request
     */
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

    /**
     * Returns the request body of the servlet request as a String.
     *
     * @return String request body
     */
    public String getBody() {
        return this.body;
    }


    private static class ConcreteServletInputstream extends ServletInputStream {


        private final ByteArrayInputStream byteArrayInputStream;

        /**
         * Creates the ConcreteServletInputstream which is a wrapper to a byte array stream.
         *
         * @param byteArrayInputStream
         * @see javax.servlet.ServletInputStream
         */
        public ConcreteServletInputstream(final ByteArrayInputStream byteArrayInputStream) {
            this.byteArrayInputStream = byteArrayInputStream;
        }

        /**
         * Overrided method of ServletInputStream
         * Reads the next byte of data from this input stream. The value byte is returned as an int in the range
         * 0 to 255. If no byte is available because the end of the stream has been reached, the value -1 is returned.
         */
        @Override
        public int read() throws IOException {
            return byteArrayInputStream.read();
        }
    }
}
