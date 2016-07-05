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
public class FCaptchaTest {

	public FCaptchaTest() {
	}

	/**
	 * Test of getImageCode method, of class ECaptcha.
	 */
	@Test
	public void testGetCaptcha() {
		try {
			System.out.println("==test getCaptcha()");
			FCaptcha instance = new FCaptcha();
			
			ImageCode imageCode = instance.getImageCode();
			ImageIO.write((RenderedImage) imageCode.getImage(), "png",
					new File("D:\\codeerTest\\" + imageCode.getCode() + ".png"));

			Double result = instance.getResult();
			System.out.println(result);

		} catch (IOException ex) {
			Logger.getLogger(FCaptchaTest.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
