package pub.greenbamboo.captcha;

/**
 * 验证码创建者接口。 它拥有获取验证码
 *
 * @see pub.greenbamboo.captcha.Captcha#getImageCode()
 * @see pub.greenbamboo.captcha.Captcha#checkCaptcha(String, String) 两个函数。
 *
 * @author bing <503718696@qq.com>
 * @date 2016-5-15 20:52:45
 * @version v0.1
 */
public interface Captcha {

    /**
     * 验证码图像和验证码字符串。
     *
     * @return 返回验证码图像和验证码字符串。
     */
    public ImageCode getImageCode();

    /**
     * 校验来自客服端输入的验证码是否相同。
     *
     * @param code 服务器端的验证码。
     * @param clientCode 来自客服端的验证码。
     * @return
     */
    public boolean checkCaptcha(String code, String clientCode);
}
