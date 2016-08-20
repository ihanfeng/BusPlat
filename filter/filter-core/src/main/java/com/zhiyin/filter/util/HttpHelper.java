package com.zhiyin.filter.util;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Created with antnest-platform
 * User: chenyuan
 * Date: 12/24/14
 * Time: 10:39 AM
 */
public class HttpHelper {

    public static boolean isPost(HttpServletRequest request) {

        if ("POST".equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        return false;
    }



        /**
         * 获取请求Body
         *
         * @param request
         * @return
         */
    public static String getBodyString(ServletRequest request) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = request.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }


    public static String extractResponseBody(final ServletWrapperOutputStream servletWrapperOutputStream) {
        return new String(servletWrapperOutputStream.toByteArray(), Charset.forName(Util.ENC_UTF8));
    }
}