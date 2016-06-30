package pub.greenbamboo.captcha;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.junit.Test;

/**
 *
 * @author bing <503718696@qq.com>
 */
public class BCaptchaTest {
    
    public BCaptchaTest() {
    }


    /**
     * Test of getImageCode method, of class BCaptcha.
     */
    @Test
    public void testGetCaptcha() {
        try {
            System.out.println("GetCaptcha");
            BCaptcha instance = new BCaptcha();
            for (int i = 0; i < 8; i++) {
                ImageCode result = instance.getImageCode();
                ImageIO.write((RenderedImage) result.getImage(), "png", new File("D:\\codeerTest\\" + result.getCode() + ".png"));
            }

        } catch (IOException ex) {
            Logger.getLogger(BCaptchaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
