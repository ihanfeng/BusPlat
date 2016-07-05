package pub.greenbamboo.captcha;

/**
 *
 * @author bing <503718696@qq.com>
 * @date 2016-5-15 23:46:16
 * @version v0.1
 */
public class CaptchaContext implements Captcha {

    private Captcha captcha;

    public CaptchaContext() {
        captcha = new DefaultCaptcha();
    }

    public CaptchaContext(Captcha codeer) {
        this.captcha = codeer;
    }

    public CaptchaContext(CaptchaTypeEnum typeEnum) {
        switch (typeEnum) {
            case ACaptcha:
                captcha = new ACaptcha();
                break;
            case BCaptcha:
                captcha = new BCaptcha();
                break;
            case CCaptcha:
                captcha = new CCaptcha();
                break;
            case DCaptcha:
                captcha = new DCaptcha();
                break;
            case ECaptcha:
                captcha = new ECaptcha();
                break;
            case FCaptcha:
                captcha = new FCaptcha();
                break;
            case GCaptcha:
                captcha = new GCaptcha();
                break;
            case JCaptcha:
                captcha = new JCaptcha();
                break;
            default:
                captcha = new DefaultCaptcha();
        }
    }

    public CaptchaContext(String CODEER_NAME) {
        switch (CODEER_NAME) {
            case ACaptcha.CODEER_NAME:
                captcha = new ACaptcha();
                break;
            case BCaptcha.CODEER_NAME:
                captcha = new BCaptcha();
                break;
            case CCaptcha.CODEER_NAME:
                captcha = new CCaptcha();
                break;
            case DCaptcha.CODEER_NAME:
                captcha = new DCaptcha();
                break;
            case ECaptcha.CODEER_NAME:
                captcha = new ECaptcha();
                break;
            case FCaptcha.CODEER_NAME:
                captcha = new FCaptcha();
                break;
            case GCaptcha.CODEER_NAME:
                captcha = new GCaptcha();
                break;
            case HCaptcha.CODEER_NAME:
                captcha = new HCaptcha();
                break;
            case JCaptcha.CODEER_NAME:
                captcha = new JCaptcha();
                break;
            default:
                captcha = new DefaultCaptcha();
        }
    }
    
    public Captcha getCaptcha() {
        return captcha;
    }

    public void setCaptcha(Captcha captcha) {
        this.captcha = captcha;
    }

    @Override
    public ImageCode getImageCode() {
        return captcha.getImageCode();
    }

    @Override
    public boolean checkCaptcha(String codeKey, String clientCode) {
        return captcha.checkCaptcha(codeKey, clientCode);
    }

}
