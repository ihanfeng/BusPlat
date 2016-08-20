package com.zhiyin.app.api.filter.encry;

import com.alibaba.fastjson.JSON;
import com.wustrive.aesrsa.util.AES;
import com.wustrive.aesrsa.util.EncryUtil;
import com.wustrive.aesrsa.util.RSA;
import com.zhiyin.app.api.encry.module.C2sRequest;
import com.zhiyin.app.logger.LoggerUtil;

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
public class NoneDecryptRequestDataWrapper extends HttpServletRequestWrapper {

    // private static final Logger accessLogger =
    // LoggerFactory.getLogger("appapi.access");

    public static final String clientPrivateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKbNojYr8KlqKD/y"
            + "COd7QXu3e4TsrHd4sz3XgDYWEZZgYqIjVDcpcnlztwomgjMj9xSxdpyCc85GOGa0"
            + "lva1fNZpG6KXYS1xuFa9G7FRbaACoCL31TRv8t4TNkfQhQ7e2S7ZktqyUePWYLlz"
            + "u8hx5jXdriErRIx1jWK1q1NeEd3NAgMBAAECgYAws7Ob+4JeBLfRy9pbs/ovpCf1"
            + "bKEClQRIlyZBJHpoHKZPzt7k6D4bRfT4irvTMLoQmawXEGO9o3UOT8YQLHdRLitW"
            + "1CYKLy8k8ycyNpB/1L2vP+kHDzmM6Pr0IvkFgnbIFQmXeS5NBV+xOdlAYzuPFkCy"
            + "fUSOKdmt3F/Pbf9EhQJBANrF5Uaxmk7qGXfRV7tCT+f27eAWtYi2h/gJenLrmtke"
            + "Hg7SkgDiYHErJDns85va4cnhaAzAI1eSIHVaXh3JGXcCQQDDL9ns78LNDr/QuHN9"
            + "pmeDdlQfikeDKzW8dMcUIqGVX4WQJMptviZuf3cMvgm9+hDTVLvSePdTlA9YSCF4"
            + "VNPbAkEAvbe54XlpCKBIX7iiLRkPdGiV1qu614j7FqUZlAkvKrPMeywuQygNXHZ+"
            + "HuGWTIUfItQfSFdjDrEBBuPMFGZtdwJAV5N3xyyIjfMJM4AfKYhpN333HrOvhHX1"
            + "xVnsHOew8lGKnvMy9Gx11+xPISN/QYMa24dQQo5OAm0TOXwbsF73MwJAHzqaKZPs"
            + "EN08JunWDOKs3ZS+92maJIm1YGdYf5ipB8/Bm3wElnJsCiAeRqYKmPpAMlCZ5x+Z"
            + "AsuC1sjcp2r7xw==";
    public static final String clientPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCmzaI2K/Cpaig/8gjne0F7t3uE"
            + "7Kx3eLM914A2FhGWYGKiI1Q3KXJ5c7cKJoIzI/cUsXacgnPORjhmtJb2tXzWaRui"
            + "l2EtcbhWvRuxUW2gAqAi99U0b/LeEzZH0IUO3tku2ZLaslHj1mC5c7vIceY13a4h"
            + "K0SMdY1itatTXhHdzQIDAQAB";
    public static final String serverPrivateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALIZ98KqgLW8IMt4"
            + "G+N+4d3DiOiEa+5s6lCMSGE/NbU9stJEqw0EuCP54MY6JkT0HCYTCrLXqww6rSQy"
            + "WF7BNCVGssk2XDcvSKiCz1ZMgabd6XVK5kvIycySydXQ0Ky6rnfxw8w2mllHABFv"
            + "s1eamaHQozv18n/XGqemjW2BFy/jAgMBAAECgYAxT3FCi3SBXKnzy7hk/z9H6Bhi"
            + "0C8V3z/stzpe+mJDYOa+wtZdD15wT4HFQFpSIwgcHo+Kvp2UEDbZ27qN2Y43AZbF"
            + "9LOalWTRUzYtr8wL8MIbgtew/QQ9YFNWdkTZ6MxCItjD/mSz3Lrkcphvbsx4VoCV"
            + "YIJ04r+Loi0t9g0guQJBANvkpfrq0bLVRYWfaigjkx47mr0trJkB7mjADe69Iqts"
            + "M/2x5dHPpClDK78yzAWxU2BrYzOd31QIOm32iMIvRxUCQQDPWJPMOzcq8Jqs1PAM"
            + "7D0hxnvF3tSJB0CJCQWdGFkJiuIYSbrWnCVF78jJyU2AK1H3RDi9BzGPL2Z3i2Si"
            + "+9kXAkAPnKtAJl3fEY9PDmNuGCCA3AB/f/eqIV345/HVSm5kt1j1oSTNAa4JE/DO"
            + "MWAU42MlDFrNtl69y5vCZOeOyeaFAkBOJieGmWcAozDZJWTYqg2cdk/eU08t2nLj"
            + "c2gPPscIRrVSzC9EhhOyWV8HVv0D6s/471inPlfajNYFBp/Goj+/AkEAiejHX/58"
            + "Vv8+ccW22RMZmyxiHcZpTw9hz7vHUCWv03+fyVGtGMhJ4xuPt8UaZm91yHSPWWar"
            + "M8Xa7errKaXN9A==";
    public static final String serverPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCyGffCqoC1vCDLeBvjfuHdw4jo"
            + "hGvubOpQjEhhPzW1PbLSRKsNBLgj+eDGOiZE9BwmEwqy16sMOq0kMlhewTQlRrLJ"
            + "Nlw3L0iogs9WTIGm3el1SuZLyMnMksnV0NCsuq538cPMNppZRwARb7NXmpmh0KM7"
            + "9fJ/1xqnpo1tgRcv4wIDAQAB";
    private final String requestData;

    public NoneDecryptRequestDataWrapper(HttpServletRequest request)
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
        /*
		 * JSONObject jsonObj = JSONObject.parseObject(encryData); C2sRequest
		 * req = new C2sRequest(); req.setData(jsonObj.getString("data"));
		 * req.setEncrykey(jsonObj.getString("encrykey")); requestData= de(req);
		 */

        String clientIP = request.getRemoteHost();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        requestData = encryData;
        String data = encryData;

        Set<Entry<String, String[]>> paramMap = request
                .getParameterMap().entrySet();
        LoggerUtil.logRequestAccess("accessinfo:" + clientIP + ";" + method
                + ";" + uri + ";" + data.trim() + ";" + JSON.toJSONString(paramMap));

        // requestDate = requestData+"fuck!!!";
    }

    public String de(C2sRequest req) throws Exception {

        // 验签
        boolean passSign = EncryUtil.checkDecryptAndSign(req.data,
                req.encrykey, clientPublicKey, serverPrivateKey);

        if (passSign) {
            // 验签通过
            String aeskey = RSA.decrypt(req.encrykey, serverPrivateKey);
            String data = AES.decryptFromBase64(req.data, aeskey);

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