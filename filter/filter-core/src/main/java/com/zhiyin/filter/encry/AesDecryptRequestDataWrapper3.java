package com.zhiyin.filter.encry;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.wustrive.aesrsa.util.AES;
import com.zhiyin.filter.config.SecurityKeyConfig;
import com.zhiyin.filter.vo.C2sRequest;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ReadListener;
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
@Slf4j
public class AesDecryptRequestDataWrapper3 extends HttpServletRequestWrapper {

    private final String requestData;

    public AesDecryptRequestDataWrapper3(HttpServletRequest request)
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
        requestData = de(req);


        String clientIP = request.getRemoteHost();
        String method = request.getMethod();
        String uri = request.getRequestURI();
//        requestData = encryData;
        String data = encryData;

        Set<Entry<String, String[]>> paramMap = request
                .getParameterMap().entrySet();

        log.info("accessinfo:" + clientIP + ";" + method
                + ";" + uri + ";" + data.trim() + ";" + JSON.toJSONString(paramMap));

        // requestDate = requestData+"fuck!!!";
    }

    public String de(C2sRequest req) throws Exception {

        // 验签
//        boolean passSign = EncryUtil.checkDecryptAndSign(req.data,
//                req.encrykey, clientPublicKey, serverPrivateKey);
        boolean passSign = true;

        if (passSign) {

            if (Strings.isNullOrEmpty(req.data)) {
                return "";
            }

            String data = AES.decryptFromBase64(req.data, SecurityKeyConfig.AesKey);

            return data;
            //
            // JSONObject jsonObj = JSONObject.parseObject(data);
            // String userid = jsonObj.getString("userid");
            // String phone = jsonObj.getString("phone");
            //
            // System.out.println("解密后的明文:userid:"+userid+" phone:"+phone);

        } else {
            System.out.println("验签失败");
        }
        return "";
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {

        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                requestData.getBytes());

        ServletInputStream servletInputStream = new ServletInputStream() {

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

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