package com.zhiyin.filter.parm;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * 修改parameter
 */
@Slf4j
public class AddParameterByNewMapFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        AddParameterByNewMapRequestWrapper requestWrapper = new AddParameterByNewMapRequestWrapper(
                (HttpServletRequest) request);

        String method = ((HttpServletRequest) request).getMethod();

        if (method.toLowerCase().equals("post")) {
            request.setCharacterEncoding("UTF-8");
            int size = request.getContentLength();

            InputStream is = request.getInputStream();
            byte[] reqBodyBytes = readBytes(is, size);

            String requestBodyStr = new String(reqBodyBytes);
            log.debug("before add param, req content len:{}, req body str:{}, request body str:{}", size, reqBodyBytes.length, requestBodyStr);

            String trimData = requestBodyStr.trim();

            if (requestBodyStr != null && requestBodyStr.length() > 0) {

                C2sBasicInfo tmp = JSON.parseObject( trimData , C2sBasicInfo.class );
                if (tmp != null && tmp.getAccess_token() != null && tmp.getAccess_token().length() > 0) {
                    requestWrapper.addParameter("access_token", tmp.getAccess_token());
                    log.debug("add request paramter, {}", tmp.getAccess_token());
                }
            }
        }

        // 使用方法
        requestWrapper.addParameter("fuch", "you");
        for (Map.Entry<String, String[]> entry : requestWrapper
                .getParameterMap().entrySet()) {
            log.debug("request parameter, key={}, value={}", entry.getKey(), entry.getValue()[0]);
        }

        chain.doFilter(requestWrapper, response);
    }

    public static final byte[] readBytes(InputStream is, int contentLen) {
        if (contentLen > 0) {
            int readLen = 0;
            int readLengthThisTime = 0;
            byte[] message = new byte[contentLen];
            try {
                while (readLen != contentLen) {

                    readLengthThisTime = is.read(message, readLen, contentLen
                            - readLen);

                    if (readLengthThisTime == -1) {// Should not happen.
                        break;
                    }

                    readLen += readLengthThisTime;
                }

                return message;
            } catch (IOException e) {
                // Ignore
                // e.printStackTrace();
            }
        }

        return new byte[]{};
    }


    public void destroy() {
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

}
