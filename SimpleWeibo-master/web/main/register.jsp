<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: vliupro
  Date: 16-5-30
  Time: 下午3:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <title>微博-微博注册</title>
    <link rel="stylesheet" type="text/css" href="<s:url value="/css/Reg-For.css" />" />
    <script type="text/javascript" src="<s:url value="/js/register.js" /> "></script>
    <script type="text/javascript" src="<s:url value="/js/jquery-1.12.4.min.js" /> "></script>
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
                    微博注册
                </div>
                <div class="W_reg_info" id="pl_account_regmobile">
                    <!--主体左侧-->
                    <div class="W_reg_form">
                        <form class="loginForm" id="loginForm" action="<s:url value="/register" />" method="post">
                            <table class="loginTable">
                                <tr>
                                    <td class="one"><i>*</i>用户名：</td>
                                    <td colspan="2" class="line_2">
                                        <input name="username" type="text" class="W_input" onblur="ifNull(this)" placeholder="用户名/4-16字符">
                                    </td>
                                    <td class="wrong"><span id="check_use"></span></td>
                                    <!--用于输出错误信息-->
                                </tr>
                                <tr>
                                    <td class="one"><i>*</i>设置密码：</td>
                                    <td colspan="2" class="line_2">
                                        <input name="password" type="password" class="W_input" placeholder="设置密码">
                                    </td>
                                    <td class="wrong"></td>
                                    <!--用于输出错误信息-->
                                </tr>
                                <tr>
                                    <td class="one"><i>*</i>验证码：</td>
                                    <td class="yan">
                                        <input name="securityCode" type="text" class="input_yan" placeholder="4位验证码" />
                                    </td>
                                    <!--随机数验证码-->
                                    <td>
                                        <!--随机数验证码显示-->
                                        <img src="<s:url value="/security/securityImage" />" id="Verify"  style="cursor:hand;" alt="看不清，换一张"/>
                                    </td>
                                    <td class="wrong"></td>
                                    <!--用于输出错误信息-->
                                </tr>
                                <tr>
                                    <td class="one"><i>*</i>邮箱：</td>
                                    <td colspan="2" class="line_2">
                                        <input name="email" type="text" class="W_input" placeholder="用于找回密码/必填">
                                    </td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <!--占位-->
                                    <td colspan="2">
                                        <input type="submit" class="W_input" id="submit" value="立即注册" />
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
                    <div class="info_list">
                        <div class="inp verify">
                            <p class="agreement"><a href="http://weibo.com/signup/v5/protocol" target="_blank">微博服务使用协议</a></p>
                            <p class="agreement"><a href="http://weibo.com/signup/v5/privacy" target="_blank">微博个人信息保护政策</a></p>
                            <p class="agreement"><a href="http://news.sina.com.cn/c/2012-12-29/051425921660.shtml" target="_blank">全国人大常委会关于加强网络信息保护的决定</a></p>
                        </div>
                    </div>
                </div>
                <!--主体左侧结束-->
                <!--主体右侧-->
                <div class="W_reg_sidebar">
                    <p class="p1_line">已有帐号，<a href="<s:url value="/toindex.jsp" />">直接登录»</a></p>
                    <div class="reg_help">
                        <p>微博注册帮助</p>
                        <ul class="help_list">
                            <li><i>1</i><a target="_blank" href="http://help.weibo.com/faq/q/76/15955#15955">微博注册操作指南</a></li>
                            <li><i>2</i><a target="_blank" href="http://help.weibo.com/faq/q/200/13091#13091">手机注册时提示手机号码已被绑定怎么办?</a></li>
                            <li><i>3</i><a target="_blank" href="http://help.weibo.com/faq/q/200/276#276">注册微博时昵称显示“已经被注册”如何处理?</a></li>
                            <li><i>4</i><a target="_blank" href="http://help.weibo.com/faq/q/200/13043#13043">注册时提示"你所使用的IP地址异常",该怎么办?</a></li>
                        </ul>
                        <a target="_blank" href="http://help.weibo.com/faq/q/201" class="help_more">更多帮助»</a>
                    </div>
                </div>
                <!--主体右侧结束-->
            </div>
        </div>
        <!--W_login_main结束-->
        <!--注册登录footer-->
        <div class="login_footer clearfix">
            <div class="left_link">
                常熟理工学院计算机科学与技术软件132班J2EE期末项目
                <a href="http://weibo.com/aj/static/jww.html">京网文[2011]0398-130号</a>
                <a href="http://www.miibeian.gov.cn/">京ICP备12002058号</a>
            </div>
            <div class="copy">
                Copyright © 2009-2016 WEIBO
            </div>
        </div>
    </div>
    <script>
        $(function () {
            //点击图片更换验证码
            $("#Verify").click(function(){
                $(this).attr("src","/security/securityImage?timestamp="+new Date().getTime());
            });
        });
    </script>
</body>

</html>
