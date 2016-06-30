package pub.greenbamboo.captcha;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * <pre>
 * 作者：ZSF
 * 项目：GreenBamboo-CAPTCHA
 * 类说明：生成算数验证码
 * 日期：2016年5月18日
 * 备注：
 * </pre>
 */
public class ECaptcha extends AbstractCaptcha {

    // 验证码类型名称
    public final static String CODEER_NAME = "ECaptcha";
    // 验证码编码数据
    private String captchaCodeSource = "0123456789";
    // 验证码运算数据（使用 Java Unicode code，加减乘除）
    private String captchaOperationSource = "\u52A0\u51CF\u4E58\u9664";
    // 验证码运算符等于
    private String captchaEqualCode = "=";
    // 验证码字体
    private Font font = new Font("Fixedsys", Font.CENTER_BASELINE, 18);

    /**
     * 返回用于构成验证码的字符
     *
     * @return
     */
    @Override
    public String generateCaptchaString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < codeLength - 1; i++) {
            if (1 == i)//追加运算符
            {
                sb.append(captchaOperationSource.charAt(random.nextInt(captchaOperationSource.length())));
            } else {
                sb.append(captchaCodeSource.charAt(random.nextInt(captchaCodeSource.length())));
            }
        }
        return sb.toString() + captchaEqualCode;
    }

    /**
     * 返回验证码图片
     *
     * @return
     */
    @Override
    public Image generateCaptchaImage(String code) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, width, height);
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, width - 1, height - 1);
        // 绘制验证码
        drawCodeString(g, code);
        return image;
    }

    /**
     * 绘制验证码
     *
     * @param Graphics 实例
     * @param code 验证码
     */
    private void drawCodeString(Graphics g, String code) {
        for (int i = 0; i < codeLength; i++) {
            g.setColor(ColorUtil.randomColor());
            g.drawString(String.valueOf(code.charAt(i)), (i + 1) * 15, 16);
        }
    }


    @Override
    public boolean checkCaptcha(String code, String clientCode) {
        if (code == null || clientCode == null || code.isEmpty() || clientCode.isEmpty() || code.length() < 4) {
            return false;
        }
        //第一位数
        int arg1 = Integer.parseInt(code.substring(0, 1));
        //操作运算符
        char opt = code.charAt(1);
        //第二位数
        int arg2 = Integer.parseInt(code.substring(2, 3));
        //结果
        int result = 0;
        switch (opt) {
            case '\u52A0'://加
                result = arg1 + arg2;
                break;
            case '\u51CF'://减
                result = arg1 - arg2;
                break;
            case '\u4E58'://乘
                result = arg1 * arg2;
                break;
            case '\u9664'://除
                if (arg2 == 0) {
                    result = 0;
                } else {
                    result = arg1 / arg2;
                }
                break;
        }
        System.out.println(" the result :" + result);
        return Integer.parseInt(clientCode) == result;
    }
}
