package pub.greenbamboo.captcha;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.junit.Test;

/**
 * 测试通过,详细见测试报告：
 * GreenBamboo-CAPTCHA\CAPTCHA\src\test\java\pub\greenbamboo\captcha\testReport\GCaptchaTest_pass_report.docx
 *
 * @author cuidianlong <1539160463@qq.com>
 */
public class GCaptchaTest {

    public GCaptchaTest() {
    }

    @Test
    public void testGetCaptcha() {
        try {
            System.out.println("getCaptcha");
            GCaptcha instance = new GCaptcha();
            for(int i = 0;i<4;i++){
                ImageCode result = instance.getImageCode();
                ImageIO.write((RenderedImage) result.getImage(), "png", new File("D:\\codeerTest\\" + result.getCode() + ".png"));
            }
        } catch (IOException ex) {
            Logger.getLogger(GCaptchaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
