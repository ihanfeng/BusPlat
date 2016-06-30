package pub.greenbamboo.captcha2;

import java.util.Arrays;

/**
 * <pre>
 * 作者：lucare
 * 项目：SpringWind-CaptchaSystem
 * 类说明：图片验证码传输类
 * 日期：2016年6月20日
 * 备注：
 * </pre>
 */
public class ValidImageInfo {

    private String key;
    private String imgName;
    private String[] imgInfo;

    public ValidImageInfo() {
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getImgName() {
        return this.imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String[] getImgInfo() {
        return this.imgInfo;
    }

    public void setImgInfo(String[] imgInfo) {
        this.imgInfo = imgInfo;
    }

    @Override
    public String toString() {
        return "ValidImageInfo{" +
                "key='" + key + '\'' +
                ", imgName='" + imgName + '\'' +
                ", imgInfo=" + Arrays.toString(imgInfo) +
                '}';
    }
}
