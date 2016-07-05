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
 * <pre>
 * 作者：ZSF
 * 项目：GreenBamboo-CAPTCHA
 * 类说明：生成算数验证码
 * 日期：2016年5月18日
 * 备注：
 * </pre>
 */
public class ECaptchaTest {
    
    public ECaptchaTest() {
    }


    /**
     * Test of getImageCode method, of class ECaptcha.
     */
    @Test
    public void testGetCaptcha() {
        try {
            System.out.println("GetCaptcha");
            ECaptcha instance = new ECaptcha();
            for (int i = 0; i < 8; i++) {
            	ImageCode result = instance.getImageCode();
                ImageIO.write((RenderedImage) result.getImage(), "png", new File("D:\\codeerTest\\" + result.getCode() + ".png"));
                boolean isSuccess = instance.checkCaptcha(result.getCode(), "5");
                System.out.println(result.getCode() + " ----the check is :" + isSuccess);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ECaptchaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
