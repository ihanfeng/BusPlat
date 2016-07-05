package pub.greenbamboo.captcha;

import java.awt.Image;

/**
 * 验证码图像和验证码实体类。
 *
 * @author bing <503718696@qq.com>
 * @date 2016-5-15 21:18:07
 * @version v0.1
 */
public class ImageCode {

    private Image image;
    private String code;

    public ImageCode() {
    }

    public ImageCode(Image image, String code) {
        this.image = image;
        this.code = code;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (this.code != null ? this.code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ImageCode other = (ImageCode) obj;
        if ((this.code == null) ? (other.code != null) : !this.code.equals(other.code)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ImageCode{" + "code=" + code + '}';
    }

}
