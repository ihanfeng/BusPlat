package pub.greenbamboo.captcha;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 *
 * <pre>
 * 作者：haibin
 * 项目：SpringWind-CaptchaSystem
 * 类说明：生成中文算数验证码
 * 日期：2016年5月20日
 * 备注：
 * </pre>
 */
public class FCaptcha extends AbstractCaptcha {

    // 验证码类型名称[?不同类型的验证码名称应该作为参数，或者配置项管理。]
    public final static String CODEER_NAME = "FCaptcha";
    // 验证码数据源[?是否考虑将生成不同验证码类型的源数据统一放到一个map,key是验证码类型,value：是数据源]
    private String[] captchaCodeSource = {"1:壹", "2:贰", "3:叁", "4:肆", "5:伍", "6:陆", "7:柒", "8:捌", "9:玖"};
    // 验证码运算数据（使用 Java Unicode code，加减乘除）
    private String[] captchaOperation = {"+:加", "-:减", "*乘:"};
    // 验证码运算符等于
    private String captchaEqualOperation = "等于";
    // 验证码字体
    private Font font = new Font("Fixedsys", Font.CENTER_BASELINE, 18);
    // 验证码个数
    int codeLength = 5;
    // 验证码图片长度
    int width = 100;
    // 运算结果
    private Double result;

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    /**
     * 返回用于构成验证码的字符
     *
     * @return
     */
    @Override
    public String generateCaptchaString() {

        try {
            String[] op1 = (captchaCodeSource[random.nextInt(captchaCodeSource.length - 1)]).split(":");
            String[] op = (captchaOperation[random.nextInt(captchaOperation.length - 1)]).split(":");
            String[] op2 = (captchaCodeSource[random.nextInt(captchaCodeSource.length - 1)]).split(":");

            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine se = manager.getEngineByName("js");
            String str = op1[0] + op[0] + op2[0];
            Double result = (Double) se.eval(str);
            setResult(result);
            return op1[1] + op[1] + op2[1] + captchaEqualOperation;
        } catch (Exception e) {
            return "";
        }

    }

    /**
     * 返回验证码图片
     *
     * @return
     */
    @Override
    public Image generateCaptchaImage(String code) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, width, height);
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, width - 1, height - 1);
        // 绘制验证码
        drawCodeString(g, code);
        return image;
    }

    /**
     * 绘制验证码
     *
     * @param Graphics 实例
     * @param code 验证码
     */
    private void drawCodeString(Graphics g, String code) {
        for (int i = 0; i < codeLength; i++) {
            g.setColor(ColorUtil.randomColor());
            g.drawString(String.valueOf(code.charAt(i)), (i + 1) * 15, 16);
        }
    }



    @Override
    public boolean checkCaptcha(String code, String clientCode) {
        return clientCode.equals(getResult());
    }
}
