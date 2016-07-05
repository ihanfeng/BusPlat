package pub.greenbamboo.InterferenceLine;


import java.awt.image.BufferedImage;

/**
 * 装饰器
 *
 * @author lengchuan <li_shuijun@163.com>
 * @date 16-6-22
 */
public class SuperLine implements IterfaceLine {

    private IterfaceLine interferenceLine;

    public SuperLine() {
    }

    public SuperLine(IterfaceLine interferenceLine) {
        this.interferenceLine = interferenceLine;
    }

    @Override
    public void setLine(BufferedImage image) {

        interferenceLine.setLine(image);

    }

    public IterfaceLine getInterferenceLine() {
        return interferenceLine;
    }

    public void setInterferenceLine(IterfaceLine interferenceLine) {
        this.interferenceLine = interferenceLine;
    }
}
