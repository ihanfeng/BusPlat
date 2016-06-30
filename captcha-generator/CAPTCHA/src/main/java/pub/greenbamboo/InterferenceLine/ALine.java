package pub.greenbamboo.InterferenceLine;

import pub.greenbamboo.captcha.ColorUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 具体实现的干扰线
 * 随机颜色，随机位置颜色的干扰线
 *
 * @author lengchuan <li_shuijun@163.com>
 * @Date 16-6-23
 */
public class ALine extends SuperLine {

    public ALine(IterfaceLine interferenceLine) {
        super(interferenceLine);
    }

    @Override
    public void setLine(BufferedImage image) {
        drawLine(image);
        super.setLine(image);
    }

    /**
     * 绘制随机的干扰线
     * @param image
     */
    public void drawLine(BufferedImage image) {
        Random random = new Random();
        Graphics g = image.getGraphics();
        g.setColor(ColorUtil.randomColor());
        int width = image.getWidth();
        int height = image.getHeight();
        for (int i = 0; i < 40; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
        g.dispose();

    }
}
