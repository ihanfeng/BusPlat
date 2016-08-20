package com.zhiyin.app.api.filter.log;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * this wrapper is needed for restoring the input stream after reading it
 *
 * @author eugen
 */
public class RequestDataWrapper extends HttpServletRequestWrapper {

    private final String requestData;

    public RequestDataWrapper(HttpServletRequest request) throws IOException {

        super(request);

        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {

            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(
                        inputStream));
                char[] charBuffer = new char[256];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }

        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }
        // stringBuilder.insert(0, "{");
        // stringBuilder.append("fuck");
        requestData = stringBuilder.toString();

        // requestDate = requestData+"fuck!!!";
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {

        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                requestData.getBytes());

        ServletInputStream servletInputStream = new ServletInputStream() {

            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };

        return servletInputStream;
    }

    @Override
    public BufferedReader getReader() throws IOException {

        return new BufferedReader(new InputStreamReader(this.getInputStream()));

    }

    /**
     * returns the request data extracted from the request
     *
     * @return
     */
    public String getRequestData() {
        return this.requestData;
    }

}