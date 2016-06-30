package pub.greenbamboo.captcha2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <pre>
 * 作者：lucare
 * 项目：SpringWind-CaptchaSystem
 * 类说明：图片验证码管理器
 * 日期：2016年6月20日
 * 备注：
 * </pre>
 */
public class ImageCodeManager {


    private static boolean started = false;

    private static List<ValidImage> images = null;

    public static synchronized void start() {
        try {
            /**
             * 此方法建议在监听器中启动  一开始查询所有验证码放到内存中
             * 这里编写获取验证码图片的方法(数据库或xml文件拿都行)
             * 从数据库中查 字段参考UserValidImage类
             */
            images = new ArrayList<ValidImage>();
            images.add(new ValidImage(1,"1001", "ambulance", "救护车", "/1001.png"));
            images.add(new ValidImage(2,"1002", "baby_bottle", "婴儿奶瓶", "/1002.png"));
            images.add(new ValidImage(3,"1003", "bandage", "卷纸", "/1003.png"));
            images.add(new ValidImage(4,"1004", "bicycle", "单车", "/1004.png"));
            images.add(new ValidImage(5,"1005", "bone", "骨头", "/1005.png"));
            images.add(new ValidImage(6,"1006", "bus", "公车", "/1006.png"));
            images.add(new ValidImage(7,"1007", "camera", "相机", "/1007.png"));
            images.add(new ValidImage(8,"1008", "capsule", "药丸", "/1008.png"));
            images.add(new ValidImage(9,"1009", "clamp", "手术钳", "/1009.png"));
            images.add(new ValidImage(11,"1011", "helicopter", "直升机", "/1011.png"));
            started = true;

        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public static boolean isRunning() {
        return started;
    }

    /**
     * 获取随机的几张图片
     * @param count 图片张数
     * @return
     */
    public static Set<ValidImage> getRandomImage(int count) {
        if (started && images != null) {
            Set<Integer> index = getRandom(count, 0, images.size());

            if (index != null) {
                Set<ValidImage> imgs = new HashSet<ValidImage>();
                for (Integer i : index) {
                    imgs.add(images.get(i.intValue()));
                }
                return imgs;
            }
        }
        return null;
    }

    public static int getRandom(int max) {
        Set<Integer> set = getRandom(1, 0, max);
        if (set != null) {
            for (Integer i : set) {
                return i.intValue();
            }
        }
        return 0;
    }

    /**
     * 获取到某个数字区间范围内的几个随机数字
     * @param count 获取的个数
     * @param min 数字区间起始
     * @param max 数字区间结束
     * @return
     */
    private static Set<Integer> getRandom(Integer count, Integer min, Integer max) {
        if (min >= max || count > (max - min)) {
            return null;
        }

        Integer intRd = 0;// 存放生成的随机数
        Set<Integer> set = new HashSet<Integer>();
        while (set.size() < count) {
            intRd = (int) (min + Math.random() * (max - min));
            set.add(intRd);
        }
        return set;
    }
}
