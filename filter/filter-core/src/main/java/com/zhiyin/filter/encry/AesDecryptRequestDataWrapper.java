package com.zhiyin.filter.encry;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.wustrive.aesrsa.util.AES;
import com.zhiyin.filter.config.SecurityKeyConfig;
import com.zhiyin.filter.util.ServletWrappperInputstream;
import com.zhiyin.filter.util.Util;
import com.zhiyin.filter.vo.C2sRequest;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 解密请求包
 *
 * @author eugen
 */
@Slf4j
public class AesDecryptRequestDataWrapper extends HttpServletRequestWrapper {

    private String body;

    public AesDecryptRequestDataWrapper(final HttpServletRequest request) {
        super(request);
        try {
            String encryptBody = Util.readInputStream(request.getInputStream());
            body = AES.decryptFromBase64( encryptBody , SecurityKeyConfig.AesKey );
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

}
