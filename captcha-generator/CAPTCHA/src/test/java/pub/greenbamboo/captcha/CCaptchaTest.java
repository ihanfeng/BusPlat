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
public class CCaptchaTest {

	public CCaptchaTest() {
	}

	/**
	 * Test of getImageCode method, of class CCaptcha.
	 */
	@Test
	public void testGetCaptcha() {
		try {
			System.out.println("GetCaptcha");
			CCaptcha instance = new CCaptcha();
			for (int i = 0; i < 10; i++) {
				ImageCode result = instance.getImageCode();

				System.out.println("Image= " + result.getImage() + " code= " + result.getCode());

				ImageIO.write((RenderedImage) result.getImage(), "png",
						new File("D:\\codeerTest\\" + result.getCode() + ".png"));
			}

		} catch (IOException ex) {
			Logger.getLogger(CCaptchaTest.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
