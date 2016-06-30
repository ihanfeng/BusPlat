package pub.greenbamboo.captcha;

import java.awt.Font;
import java.awt.Image;

/**
 * 验证码创建者实体类C。该类是一个未完成的实现。请后续开发者完善。
 *
 * @author bing <503718696@qq.com>
 * @date 2016-5-15 21:08:41
 * @version v0.1
 */
public class CCaptcha extends AbstractCaptcha {

    public final static String CODEER_NAME = "CCaptcha";
    private String randString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";//用于生产的母字符串
    private Font font = new Font("Fixedsys", Font.CENTER_BASELINE, 18);//验证码字体

    public CCaptcha() {
    }

    @Override
    public String generateCaptchaString() {
        return null;
    }

    @Override
    public Image generateCaptchaImage(String code) {
        return null;
    }

}
