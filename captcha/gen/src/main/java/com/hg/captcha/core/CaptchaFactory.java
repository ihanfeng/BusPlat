package com.hg.captcha.core;

import org.patchca.color.SingleColorFactory;
import org.patchca.filter.predefined.CurvesRippleFilterFactory;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;

import javax.xml.bind.DatatypeConverter;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by wangqinghui on 2016/3/4.
 */
public class CaptchaFactory {


    public static CaptchaBase64 genBase64(){
        ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
        cs.setColorFactory(new SingleColorFactory(new Color(25, 60, 170)));
        cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));

        try {

            CaptchaBase64 base64 = new CaptchaBase64();


            ByteArrayOutputStream fos = new ByteArrayOutputStream();

            String ch = EncoderHelper.getChallangeAndWriteImage(cs, "png", fos);
            base64.setValue(ch);
            base64.setBase64( DatatypeConverter.printBase64Binary(fos.toByteArray()) );
            return base64;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
