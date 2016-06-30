package pub.greenbamboo.InterferenceLine;


import java.awt.image.BufferedImage;

/**
 * 干扰线接口
 * 这里使用装饰器模式
 * 作为一个抽象组件
 * @author lengchuan <li_shuijun@163.com>
 * @date 16-6-22
 */
public interface IterfaceLine {

    /**
     * 设置干扰线
     * @param image
     */
    public void setLine(BufferedImage image);

}
