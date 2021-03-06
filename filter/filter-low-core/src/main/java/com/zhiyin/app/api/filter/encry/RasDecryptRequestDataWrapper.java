package com.zhiyin.app.api.filter.encry;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.wustrive.aesrsa.util.RSA;
import com.zhiyin.app.api.encry.module.C2sRequest;
import com.zhiyin.app.api.system.config.SecurityKeyConfig;
import com.zhiyin.app.logger.LoggerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 解密请求包
 *
 * @author eugen
 */
public class RasDecryptRequestDataWrapper extends HttpServletRequestWrapper {

    private static final Logger log =
            LoggerFactory.getLogger("appapi.access");

    private final String requestData;

    public RasDecryptRequestDataWrapper(HttpServletRequest request)
            throws Exception {

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

        // 如果加密，返回解密结果
        String encryData = stringBuilder.toString();

        JSONObject jsonObj = JSONObject.parseObject(encryData);
        C2sRequest req = new C2sRequest();
        req.setData(jsonObj.getString("data"));
        req.setEncrykey(jsonObj.getString("encrykey"));
        requestData = de(req);


        String clientIP = request.getRemoteHost();
        String method = request.getMethod();
        String uri = request.getRequestURI();
//        requestData = encryData;
        String data = encryData;

        Set<Entry<String, String[]>> paramMap = request
                .getParameterMap().entrySet();
        LoggerUtil.logRequestAccess("accessinfo:" + clientIP + ";" + method
                + ";" + uri + ";" + data.trim() + ";" + JSON.toJSONString(paramMap));

        // requestDate = requestData+"fuck!!!";
    }

    public String de(C2sRequest req) throws Exception {

        // 验签
//        boolean passSign = EncryUtil.checkDecryptAndSign(req.data,
//                req.encrykey, clientPublicKey, serverPrivateKey);
        boolean passSign = true;

        // 验签通过
        if (passSign) {
            if (Strings.isNullOrEmpty(req.data)) {
                return "";
            }

            String deData = RSA.decrypt(req.data, SecurityKeyConfig.serverPrivateKey);

            log.info("decry data:{}", deData);
            return deData;
        } else {
            log.error("not pass data sign.");
        }
        return "";
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