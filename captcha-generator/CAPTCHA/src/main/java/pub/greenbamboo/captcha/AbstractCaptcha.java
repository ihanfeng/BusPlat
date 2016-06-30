package pub.greenbamboo.captcha;

import java.awt.Image;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * 验证码创建者抽象类。它只实现了@see pub.greenbamboo.captcha.Captcha接口。
 *
 * @author bing <503718696@qq.com>
 * @date 2016-5-15 20:52:45
 * @version v0.1
 */
public abstract class AbstractCaptcha implements Captcha {

    protected List<ImageCode> list;//验证码图像和验证码缓存裂表。
    protected Random random = new Random();
    protected int width = 80;//图片宽
    protected int height = 26;//图片高
    protected int codeLength = 4;//验证码个数

    public AbstractCaptcha() {
        list = Collections.synchronizedList(new LinkedList<ImageCode>());
    }

    /**
     * 获取验证码图像和验证码。
     *
     * @return 返回验证码图像和验证码。
     */
    @Override
    public ImageCode getImageCode() {
        int size = list.size();
        if (size < 1000) {
            ImageCode nvp = generateCodeImage();
            list.add(nvp);
            return nvp;
        } else {
            return list.get(random.nextInt(size));
        }
    }

    /**
     * 创建图像验证码。
     *
     * @return 返回图像验证码。
     */
    public ImageCode generateCodeImage() {
        String captcha = generateCaptchaString();
        System.out.println(captcha);
        Image captchaImage = generateCaptchaImage(captcha);
        ImageCode imageCode = new ImageCode(captchaImage, captcha);
        return imageCode;
    }

    /**
     * 创建验证码字符串。
     *
     * @return 返回验证码字符串。
     */
    public abstract String generateCaptchaString();

    /**
     * 创建验证码图像。
     *
     * @param code 输入的验证码字符串。
     * @return 返回验证码图像。
     */
    public abstract Image generateCaptchaImage(String code);

    @Override
    public boolean checkCaptcha(String code, String clientCode) {
        if (code == null || clientCode == null || code.isEmpty() || clientCode.isEmpty() || code.length() != codeLength) {
            return false;
        }
        return code.equals(clientCode);
    }

}
