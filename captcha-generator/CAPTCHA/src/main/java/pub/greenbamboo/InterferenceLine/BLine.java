package pub.greenbamboo.InterferenceLine;

import pub.greenbamboo.captcha.ColorUtil;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;

/**
 * 波浪线
 *
 * @author lengchuan <li_shuijun@163.com>
 * @Date 16-6-23
 */
public class BLine extends SuperLine {

    public BLine(IterfaceLine interferenceLine) {
        super(interferenceLine);
    }

    @Override
    public void setLine(BufferedImage image) {
        drawLine(image);
        super.setLine(image);
    }

    /**
     * 绘制波浪线
     */
    public void drawLine(BufferedImage image) {
        GeneralPath gp = new GeneralPath();
        long height = image.getHeight();
        gp.moveTo(0, height / 2);
        Graphics2D g2d = (Graphics2D) image.getGraphics();
        g2d.setColor(ColorUtil.randomColor());
        for (double i = 0; i <= 8 * Math.PI; i += 0.0001 * Math.PI) {
            gp.lineTo(10 * i, height / 2 + 5 * Math.sin(2 * i));
        }
        g2d.draw(gp);
        g2d.draw(gp);
        g2d.dispose();

    }
}
