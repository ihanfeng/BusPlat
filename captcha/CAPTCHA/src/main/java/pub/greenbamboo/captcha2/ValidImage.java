package pub.greenbamboo.captcha2;

/**
 * <pre>
 * 作者：lucare
 * 项目：SpringWind-CaptchaSystem
 * 类说明：验证码图片实体类
 * 日期：2016年6月20日
 * 备注：
 * </pre>
 */
public class ValidImage {

    private int id;

    private String uid;

    private String name;

    private String enname;

    private String imgsrc;

    public ValidImage() {
    }

    public ValidImage(int id, String uid, String enname, String name, String imgsrc) {
        this.id = id;
        this.uid = uid;
        this.name = name;
        this.enname = enname;
        this.imgsrc = imgsrc;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnname() {
        return this.enname;
    }

    public void setEnname(String enname) {
        this.enname = enname;
    }

    public String getImgsrc() {
        return this.imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }
}
