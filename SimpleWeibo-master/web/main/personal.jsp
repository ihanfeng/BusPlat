<%--
  Created by IntelliJ IDEA.
  User: vliupro
  Date: 16-5-30
  Time: 下午3:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>

<head>
    <meta charset="utf-8">
    <title>个人中心</title>
    <link rel="stylesheet" type="text/css" href="<s:url value="/css/index.css" /> " />
    <link rel="stylesheet" type="text/css" href="<s:url value="/css/myindex.css" /> " />
    <script src="<s:url value="/js/myindex.js" />" type="text/javascript"></script>
</head>

<body>
    <div class="WB_myindex">
        <div class="WB_mymain">
            <div class="blog_top">
                <!--顶部导航栏-->
                <div class="top_inner clearfix">
                    <div class="top_logo">
                        <a href="<s:url value="/main/index.jsp" />"><img src="<s:url value="/images/logo2.png" />" /><em><h2 class="logo_t">微博</h2></em></a>
                    </div>
                    <!--顶部logo-->
                    <div class="search">
                        <input type="text" node-type="searchInput" class="search_input" placeholder="你想知道的这里都有……" />
                        <input type="button" value="搜索" title="搜索" id="ficon_search" class="ficon_search" onmouseover="color()" onmouseout="colorout()" onclick="" />
                    </div>
                    <!--搜索框-->
                    <div class="top_nav">
                        <ul class="nav_list">
                            <!--顶部右侧首页和个人-->
                            <li>
                                <a href="<s:url value="/main/index.jsp" />" title="首页" onMouseOver="Onp(0)" onMouseOut="Offp(0)">
                                    <img src="<s:url value="/images/home.png" />" class="list_img" name="img0" onmousemove="color()" />
                                    <span>首页</span>
                                </a>
                            </li>
                            <li>
                                <a href="<s:url value="/main/personal.jsp" />" title="">
                                    <img src="<s:url value="/images/person.png" />" class="list_img" id="list_img" />
                                    <span class="nav_span1">用户名</span>
                                </a>
                            </li>
                            <li>|</li>
                            <li>
                                <a href="" onclick="">退出</a>
                            </li>
                        </ul>
                    </div>
                    <!--消息提醒和个人-->
                </div>
            </div>
            <!--顶部导航栏到此结束-->
            <!--中间主要内容-->
            <div class="per_main">
                <div class="per_left">
                    <!--左边个人发布的微博、消息显示-->
                </div>
                <div class="per_right">
                    <!--右边个人资料卡、导航栏（包括：我的资料，我的消息）-->
                </div>
            </div>
            <!--中间主要内容结束-->
        </div>
    </div>
    <div id="backtop" style="display: block;">
        <a href="#top"></a>
    </div>
    <div id="b_foot"></div>
    <!---底部footer开始-->
    <div class="footer">
        <div class="footer_link clearfix">
            <dl class="list">
                <dt>合作&推荐 </dt>
                <dd>联系邮箱：bragg_chen@163.com</dd>
                <dd><a href="http://www.baidu.com/" target="_blank">百度搜索</a></dd>
            </dl>
            <dl class="list">
                <dt>微博帮助</dt>
                <dd><a href="http://www.baidu.com/" target="_blank">意见反馈</a></dd>
                <dd><a href="http://www.baidu.com/" target="_blank">常见问题</a></dd>
            </dl>
            <dd><a target="_blank" href="http://weibo.com/aj/static/jicp.html?_wv=6">京ICP证100780号</a> </dd>
            <dd><a href="http://www.miibeian.gov.cn" target="__blank">京ICP备12002058号</a></dd>
        </div>
        <div class="footer_link2">
            <span>Copyright © 2009-2016 WEIBO 常熟理工学院软件132班J2EE</span>
            <a target="_blank" href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=11000002000019"><img src="<s:url value="/images/gongan.jpg" />" />京公网安备11000002000019号</a>
        </div>
    </div>
    <!---底部信息栏结束-->
</body>

</html>
