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
 * @author jlee <524763542@qq.com>
 */
public class JCaptchaTest {

    @Test
    public void testGetCaptcha() {
        try {
            JCaptcha instance = new JCaptcha();
            for (int i = 0; i < 8; i++) {
                ImageCode result = instance.getImageCode();
                ImageIO.write((RenderedImage) result.getImage(), "png", new File("../image/" + result.getCode() + ".png"));
            }

        } catch (IOException ex) {
            Logger.getLogger(ACaptchaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

