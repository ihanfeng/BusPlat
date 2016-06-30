package pub.greenbamboo.captcha;

import org.junit.BeforeClass;
import org.junit.Test;
import pub.greenbamboo.captcha2.ImageCodeManager;
import pub.greenbamboo.captcha2.ValidImage;
import pub.greenbamboo.captcha2.ValidImageInfo;

import java.util.Set;

/**
 * Created by Lucare.Feng on 2016/6/20.
 */
public class ImgCodeTest {

    @BeforeClass
    public static void init() {
        ImageCodeManager.start();
    }

    @Test
    public void testGetImgCode() {
        int count = 5;
        String lang = "en";//可以根据缓存的用户信息查找他的语言
        /* 图片验证码问题和答案 */
        String key = java.util.UUID.randomUUID().toString();
        String value = null;

        Set<ValidImage> imgs = ImageCodeManager.getRandomImage(count);
        ValidImageInfo image = new ValidImageInfo();
        int k = 0;
        int intrd = ImageCodeManager.getRandom(count); // 随机获取到答案图片

		/* 生成客户端需要的验证码信息 */
        String[] imgInfo = new String[imgs.size()];
        for (ValidImage i : imgs) {
            if (k == intrd) {
                image.setImgName("en".equals(lang) ? i.getEnname() : i.getName());
                value = i.getUid();
            }
            imgInfo[k++] = i.getUid();
        }
        image.setKey(key);
        image.setImgInfo(imgInfo);
        System.out.println(image);
        System.out.println(value);

        /**
         * 将ValidImageInfo对象传到前台 value保存到缓存中
         * 验证时前台传来 key和选中的value   将这个value和缓存里的value比较
         */

		/* 把图片的验证信息存到缓存，等待验证*/
    }

}
