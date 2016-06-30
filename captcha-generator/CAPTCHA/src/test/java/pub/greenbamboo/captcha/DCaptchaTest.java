package pub.greenbamboo.captcha;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * <pre>
 * 作者：haibin
 * 项目：GreenBamboo-CAPTCHA
 * 类说明：生成汉字验证码单元测试
 * 日期：2016年5月17日
 * 备注：
 * </pre>
 */
public class DCaptchaTest {

	public DCaptchaTest() {
	}

	// 验证码实体
	private ImageCode captchaEntity;
	// 验证码处理类
	private DCaptcha captchaHandler;
	// 验证码图片
	private RenderedImage captchaImage;
	// 图片文件格式
	private String imageFileType;
	// 图片文件存放路径
	private String imageSavePath;
	// 生成验证码个数
	private int captchaNum = 8;

	@Before
	public void testInit() {
		captchaEntity = new ImageCode();
		captchaHandler = new DCaptcha();
		captchaImage = (RenderedImage) captchaHandler.getImageCode().getImage();
		imageFileType = ".png";
		imageSavePath = "D:\\codeerTest\\";
	}

	/**
	 * Test of getImageCode method, of class DCaptcha.
	 */
	@Test
	public void testGetCaptcha() {
		try {
			for (int i = 0; i < captchaNum; i++) {
				ImageIO.write(captchaImage, "png", new File(imageSavePath + captchaEntity.getCode() + imageFileType));
			}

		} catch (IOException ex) {
			Logger.getLogger(DCaptchaTest.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Test
	public void testCheckCaptcha() {

		String captchaSource = captchaHandler.generateCaptchaString();
		String captcha = captchaHandler.getImageCode().getCode();
		System.out.println(captchaSource + " " + captcha);
		Assert.assertFalse(captchaSource.equals(captcha));
	}

	@After
	public void testAfter() {
		// TODO
	}

}
