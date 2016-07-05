package pub.greenbamboo.captcha;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * 验证码创建者实体类B。该类是一个未完成的实现。请后续开发者完善。
 *
 * @author bing <503718696@qq.com>
 * @date 2016-5-15 21:08:41
 * @version v0.1
 */
public class BCaptcha extends AbstractCaptcha {

    public final static String CODEER_NAME = "BCaptcha";
    private char[] codeSequence = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
        'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
        'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};//用于生产的母字符数组
    private Font font = new Font("Fixedsys", Font.BOLD, 18);//验证码字体

    public BCaptcha() {
    }

    @Override
    public String generateCaptchaString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < codeLength; i++) {
            sb.append(codeSequence[random.nextInt(codeSequence.length)]);
        }
        return sb.toString();
    }

    @Override
    public Image generateCaptchaImage(String code) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics gd = bi.getGraphics();
        gd.setColor(Color.WHITE);
        gd.fillRect(0, 0, width, height);
        gd.setFont(font);
        gd.setColor(Color.BLACK);
        gd.drawRect(0, 0, width - 1, height - 1);
        drawCodeString(gd, code);
        return bi;
    }


    public void drawCodeString(Graphics gd, String code) {
        for (int i = 0; i < codeLength; i++) {
            gd.setColor(ColorUtil.randomColor());
            gd.drawString(String.valueOf(code.charAt(i)), (i + 1) * 15, 16);
        }
    }

}
