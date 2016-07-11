<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: vliupro
  Date: 16-5-30
  Time: 下午2:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <title>微博-密码找回</title>
    <link rel="stylesheet" type="text/css" href="<s:url value="/css/Reg-For.css" />" />
</head>

<body>
    <div class="W_login">
        <div class="W_reg_header">
            <div class="W_logo"><img src="<s:url value="/images/logo2.png" />" />
                <p><em>微博</em></p>
            </div>
        </div>
        <div class="W_login_main clearfix">
            <div class="login_cont">
                <div class="main_tab_line">
                    密码找回
                </div>
                <div class="forget_main" id="forget_main">
                    <!--主体-->
                    <p class="forget_text">您的帐号已绑邮箱<span> 1111111@110.com </span>，您可以通过邮箱接收验证码找回密码</p>
                    <div class="forget_main">
                        <table>
                            <tr>
                                <td class="line_1">绑定邮箱：</td>
                                <!--每个td都设置了固定的宽度-->
                                <td><span>1111111@110.com</span></td>
                            </tr>
                            <tr>
                                <td class="line_1">验证码：</td>
                                <td class="line_2">
                                    <input type="text" placeholder="4位验证码" class="forget_input" />
                                </td>
                                <td>
                                    <input type="button" value="获取验证码" onclick="" class="button_get" />
                                </td>
                                <td class="wrong">验证码错误</td>
                                <!--错误信息-->
                            </tr>
                            <tr>
                                <td class="line_1">新密码：</td>
                                <td class="line_2">
                                    <input type="text" class="forget_input" placeholder="4-16位字符/字母或数字" />
                                </td>
                                <td colspan="2" class="wrong">格式错误</td>
                                <!--检测密码格式是否符合要求-->
                            </tr>
                            <tr>
                                <td class="line_1">确认密码：</td>
                                <td class="line_2">
                                    <input type="text" class="forget_input" placeholder="确认新密码" />
                                </td>
                                <td colspan="2" class="wrong">两次密码不一样</td>
                                <!--检测两次密码是否相同-->
                            </tr>
                            <tr>
                                <td class="line_1"></td>
                                <td>
                                    <input type="submit" value="确认" onclick="" class="forget_submit" />
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <div class="login_footer clearfix">
            <div class="left_link">
                常熟理工学院计算机科学与技术软件132班J2EE期末项目
                <a href="http://weibo.com/aj/static/jww.html">京网文[2011]0398-130号</a>
                <a href="http://www.miibeian.gov.cn/">京ICP备12002058号</a>
            </div>
            <div class="copy">
                <span>Copyright © 2009-2016 WEIBO</span>
            </div>
        </div>
    </div>
</body>

</html>
