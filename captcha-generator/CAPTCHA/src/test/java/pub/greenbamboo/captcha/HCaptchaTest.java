package pub.greenbamboo.captcha;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 测试通过,详细见测试报告：
 * GreenBamboo-CAPTCHA\CAPTCHA\src\test\java\pub\greenbamboo\captcha\testReport\HCaptchaTest_pass_report.docx
 *
 * @author 冷川 <li_shuijun@163.com>
 * @date 2016-6-22
 */
public class HCaptchaTest {

    @Test
    public void testGetCaptcha() {
        try {
            HCaptcha instance = new HCaptcha();
            for (int i = 0; i < 8; i++) {
                ImageCode result = instance.getImageCode();
                ImageIO.write((RenderedImage) result.getImage(), "png", new File("../image/" + result.getCode() + ".png"));
            }

        } catch (IOException ex) {
            Logger.getLogger(ACaptchaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
