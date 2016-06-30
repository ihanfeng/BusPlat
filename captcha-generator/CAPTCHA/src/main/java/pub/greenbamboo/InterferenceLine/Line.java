package pub.greenbamboo.InterferenceLine;



import java.awt.image.BufferedImage;

/**
 * 真实的干扰线对象
 * 也就是被装饰器装饰的对象
 * @author lengchuan <li_shuijun@163.com>
 * @Date 16-6-23
 */
public class Line implements IterfaceLine{

    /**
     *设置干扰线
     * @param image
     */
    @Override
    public void setLine(BufferedImage image) {
        //默认不设置干扰线
        //这里也可以对图形进行各种操作
    }
}
