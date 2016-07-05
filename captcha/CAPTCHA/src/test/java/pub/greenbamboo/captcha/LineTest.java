package pub.greenbamboo.captcha;

import org.junit.Test;
import pub.greenbamboo.InterferenceLine.ALine;
import pub.greenbamboo.InterferenceLine.BLine;
import pub.greenbamboo.InterferenceLine.Line;
import pub.greenbamboo.captcha.CaptchaContext;
import pub.greenbamboo.captcha.CaptchaTypeEnum;
import pub.greenbamboo.captcha.ImageCode;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
/**
 * @author lengchuan <li_shuijun@163.com>
 * @Date 16-6-23
 */
public class LineTest {

    @Test
    public void testALinesetLine() {
        CaptchaContext context = new CaptchaContext();
        ImageCode imageCode = context.getImageCode();
        ALine aLine = new ALine(new Line());
        aLine.setLine((BufferedImage) imageCode.getImage());
        try {
            ImageIO.write((RenderedImage) imageCode.getImage(), "png", new File("../image/" + imageCode.getCode() + "_ALine.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBLinesetLine() {
        CaptchaContext context = new CaptchaContext();
        ImageCode imageCode = context.getImageCode();
        BLine bLine = new BLine(new Line());
        bLine.setLine((BufferedImage) imageCode.getImage());
        try {
            ImageIO.write((RenderedImage) imageCode.getImage(), "png", new File("../image/" + imageCode.getCode() + "_BLine.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testALine_BLinesetLine() {
        CaptchaContext context = new CaptchaContext();
        ImageCode imageCode = context.getImageCode();
        BLine bLine = new BLine(new ALine(new Line()));
        bLine.setLine((BufferedImage) imageCode.getImage());
        try {
            ImageIO.write((RenderedImage) imageCode.getImage(), "png", new File("../image/" + imageCode.getCode() + "_ALine_BLine.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAllLine_AllCodder() {
        CaptchaContext context = null;
        ALine aLine = new ALine(new Line());
        BLine bLine = new BLine(new Line());
        BLine a_bLine = new BLine(aLine);
        try {
            for (CaptchaTypeEnum typeEnum : CaptchaTypeEnum.values()) {
                context = new CaptchaContext(typeEnum);
                ImageCode imageCode = context.getImageCode();
                if (null != imageCode.getImage()) {
                    aLine.setLine((BufferedImage) imageCode.getImage());
                    ImageIO.write((RenderedImage) imageCode.getImage(), "png", new File("../image/" + imageCode.getCode() + "_" + typeEnum.toString() + "_ALine.png"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            for (CaptchaTypeEnum typeEnum : CaptchaTypeEnum.values()) {
                context = new CaptchaContext(typeEnum);
                ImageCode imageCode = context.getImageCode();
                if (null != imageCode.getImage()) {
                    bLine.setLine((BufferedImage) imageCode.getImage());
                    ImageIO.write((RenderedImage) imageCode.getImage(), "png", new File("../image/" + imageCode.getCode() + "_" + typeEnum.toString() + "_BLine.png"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            for (CaptchaTypeEnum typeEnum : CaptchaTypeEnum.values()) {
                context = new CaptchaContext(typeEnum);
                ImageCode imageCode = context.getImageCode();
                if (null != imageCode.getImage()) {
                    a_bLine.setLine((BufferedImage) imageCode.getImage());
                    ImageIO.write((RenderedImage) imageCode.getImage(), "png", new File("../image/" + imageCode.getCode() + "_" + typeEnum.toString() + "_ALine_BLine.png"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
