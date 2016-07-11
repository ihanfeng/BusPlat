<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: vliupro
  Date: 16-5-20
  Time: 下午2:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<s:url action="api/thumbing" var="thumbing_url"/>
<s:url action="api/comment" var="comment_url"/>
<s:url action="forward" var="forward_url"/>
<s:url action="loginIndex" var="loginIndex_url"/>
<s:url action="index" var="index_url"/>
<s:url action="others" var="others_url"/>
<s:url action="logout" var="logout_url"/>
<html>

<head>
    <meta charset="utf-8">
    <title>微博-随时随地发现新鲜事</title>
    <link rel="stylesheet" type="text/css" href="<s:url value="css/index.css" />"/>
</head>

<body>
<div id="index_body">
    <!---对每个页面的主体都设置唯一的ID-->
    <div class="index_header">
        <!---上半部分包括背景、logo和登录板块-->
        <div class="header_bg">
            <!--在CSS中设置背景图片-->
            <div class="header_logo">
                <img src="<s:url value="/images/logo2.png" />"/>
                <P><em>简易微博</em></P>
            </div>
            <s:if test="#session.user==null">
                <div class="login_box" id="login_form">
                    <!--登录表单开始-->
                    <div class="box_content">
                        <div class="login_box_title">
                            <p>帐号登录</p>
                        </div>
                        <hr/>
                        <form class="login" action="login" method="post">
                                <%--登陆出错--%>
                            <s:actionerror/>
                            <div class="input_wrap" id="username">
                                <img src="<s:url value="/images/user.png" />"/>
                                <input type="text" name="username" placeholder="用户名"/>
                            </div>
                            <div class="input_wrap" id="password">
                                <img src="<s:url value="/images/lock.png" />"/>
                                <input type="password" name="password" placeholder="请输入密码"/>
                            </div>
                            <div class="loginbox_line">
                                <div class="remember">
                                    <label for="remember"></label>
                                    <input id="remember" type="checkbox" name="remember" checked="checked"/>
                                    <span class="txt1">记住我</span>
                                </div>
                                <div class="right_forget">
                                    <span><a href="<s:url value="/main/forget.jsp" />" target="_blank" class="txt2">忘记密码？</a></span>
                                </div>
                            </div>
                            <div class="loginbox_line">
                                <input type="submit" value="登录" class="Sub"/>
                            </div>
                        </form>
                        <div class="loginbox_line">
                            <span class="txt1">还没有微博？</span><span class="txt2"><a
                                href="<s:url value="/main/register.jsp" />" target="_blank">立即注册</a></span>
                        </div>
                        <hr/>
                    </div>
                </div>
                <!---登录表单结束--->
            </s:if>
        </div>
    </div>
    <!--header结束--->
    <!--WB_main开始 主体内容-->
    <div class="WB_main">
        <!--外结构-->
        <!--内结构-->
        <div class="main_frame">
            <div class="WB_frame_a">
                <!--左侧微博内容-->
                <!--每一条微博的格式-->
                <s:iterator value="%{#request.page.items}" id="weibo">
                    <s:if test="%{#weibo.original}">
                        <div class="WB_frame_content">
                            <div class="content_detail clearfix">
                                <!--微博详情-->
                                <div class="user_info">
                                    <!--用户信息-->
                                    <div class="userPic">
                                        <a href="${others_url}?userId=${weibo.userId}" target="_blank" title="">
                                            <img src="<s:url
                                             value="/images/WB_frame_content/userPic.jpg" />" title=""/>
                                        </a>
                                    </div>
                                    <div class="userName">
                                        <a href="${others_url}?userId=${weibo.userId}" target="_blank" title="" class="name"><s:property
                                                value="#request.idMap[#weibo.userId]"/></a>
                                        <p><s:date name="#weibo.wCtime" format="yyyy-MM-dd HH:mm:ss"/></p>
                                    </div>
                                </div>
                                <div class="WB_text">
                                    <!--微博文字内容-->
                                    <s:property value="#weibo.wContent"/>
                                </div>
                            </div>
                            <div class="WB_handle">
                                <!---微博互动栏点赞评论等-->
                                <div class="WB_handle_in">
                                    <ul>
                                        <li class="forward">
                                            <a class="forward_a" href="javascript:void(0);" title="转发">
                                                <span class="spa">
                                                    <img src="<s:url value="/images/share.png" />"/>
                                                    <span class="handtxt">转发</span>
                                                    <em><s:property
                                                            value="%{#request.numForwardMap[#weibo.weiboId]}"/></em>
                                                </span>
                                            </a>
                                        </li>
                                        <li class="curr">
                                            <a class="comment-a" href="javascript:void(0);" title="评论">
                                                <span class="spa">
                                                    <img src="<s:url value="/images/Dialog.png" />"/>
                                                    <span class="handtxt">评论</span>
                                                    <em><s:property
                                                            value="%{#request.commentNumMap[#weibo.weiboId]}"/></em>
                                                </span>
                                            </a>
                                        </li>
                                        <li class="thumb">
                                            <s:if test="%{#session.user!=null && #request.thumbMap[#weibo.weiboId]}">
                                                <%--已被赞--%>
                                                <a class="thumbed" title="已赞"
                                                   data-wid="<s:property value="#weibo.weiboId" />">
                                                    <span class="spa"><img
                                                            src="<s:url value="/images/up.png" />"/>
                                                        <span class="handtxt">已赞</span>
                                                        <em><s:property
                                                                value="%{#request.numThumbMap[#weibo.weiboId]}"/></em>
                                                    </span>
                                                </a>
                                            </s:if>
                                            <s:else>
                                                <a class="thumbing" href="javascript:void(0);" title="赞"
                                                   data-wid="<s:property value="#weibo.weiboId" />">
                                                    <span class="spa"><img
                                                            src="<s:url value="/images/up.png" />"/>
                                                        <span class="handtxt">赞</span>
                                                        <em><s:property
                                                                value="%{#request.numThumbMap[#weibo.weiboId]}"/></em>
                                                    </span>
                                                </a>
                                            </s:else>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <!--点赞评论区域结束-->
                            <!--互动评论区域display:none--->
                            <div class="handle_comt" id="handle_comt" style="display:none">
                                <div class="comt_list">
                                    <div class="comt_publish">
                                        <!---发布信息框-->
                                        <div class="WB_publish clearfix">
                                            <div class="p_input">
                                                <textarea class="W_input" action-type="check" cols=""
                                                          rows=""></textarea>
                                            </div>
                                            <div class="p_opt">
                                                <input type="submit" class="p_opt_btn" value="评论"/>
                                            </div>
                                        </div>
                                    </div>
                                    <!--发布框结束-->
                                    <div class="repeat_list">
                                        <s:iterator value="%{#request.commentMap[#weibo.weiboId]}" id="comment">
                                            <!--评论内容外框架-->
                                            <div class="list_box">
                                                <!--评论内容内框架-->
                                                <div class="list_li clearfix">
                                                    <div class="WB_face">
                                                        <img src="<s:url
                                                        value="/images/WB_frame_content/userPic.jpg"/>"/>
                                                    </div>
                                                    <div class="list_con clearfix">
                                                        <span class="WB_username">
                                                            <s:property value="%{#request.idMap[#comment.userId]}"/>
                                                        </span>
                                                        <!--用户名-->
                                                        <br/>
                                                        <p class="repeat_text">
                                                            <s:property value="#comment.cContent"/>
                                                        </p>
                                                        <!--评论的内容-->
                                                    </div>
                                                </div>
                                            </div>
                                        </s:iterator>
                                    </div>
                                    <!--用户评论内容区结束-->
                                </div>
                            </div>
                            <!--互动评论区结束-->
                        </div>
                    </s:if>
                    <s:else>
                        <%--放置转发微博--%>
                        <!--每一条 “转发微博” 微博的格式-->
                        <div class="WB_frame_content">
                            <div class="content_detail clearfix">
                                <!--微博详情-->
                                <div class="user_info clearfix">
                                    <!--用户信息-->
                                    <div class="userPic">
                                        <a href="${others_url}?userId=${weibo.userId}" target="_blank" title="">
                                            <img src="<s:url
                                            value="/images/WB_frame_content/userPic.jpg"/>" title=""/>
                                        </a>
                                    </div>
                                    <div class="userName">
                                        <a href="${others_url}?userId=${weibo.userId}" target="_blank" title="" class="name">
                                            <s:property value="#request.idMap[#weibo.userId]"/>
                                        </a>
                                        <p>
                                            <s:date name="#weibo.wFtime" format="yyyy-MM-dd HH:mm:ss"/>
                                        </p>
                                    </div>
                                </div>

                                <div class="discuss"><s:property value="#weibo.remark"/></div>
                                <!--转发者的评论--当没有评论内容的时候，系统添加“转发微博”-->
                                <div class="resend">
                                    <!--转发的原创者微博内容-->
                                    <div class="re_userName">
                                        <a href="" target="_blank" title="" class="name">
                                            <span>@</span>
                                            <s:property value="#request.originUserMap[#weibo.weiboId]"/>
                                        </a>
                                    </div>
                                    <div class="WB_text">
                                        <!--微博文字内容-->
                                        <s:property value="#weibo.wContent"/>
                                    </div>
                                        <%--<div class="WB_media clearfix">--%>
                                        <%--<!--微博图片-->--%>
                                        <%--<div class="mediabox clearfix">--%>
                                        <%--<a target="_blank" href="images/WB_frame_content/media-pic.jpg">--%>
                                        <%--<img src="../images/WB_frame_content/media-pic.jpg" />--%>
                                        <%--</a>--%>
                                        <%--</div>--%>
                                        <%--</div>--%>
                                </div>
                                <!--转发的原创者微博内容结束-->
                            </div>
                            <div class="WB_handle">
                                <!---微博互动栏点赞评论等-->
                                <div class="WB_handle_in">
                                    <ul>
                                        <li class="forward">
                                            <a class="forward_a" href="javascript:void(0);" title="转发">
                                                <span class="spa">
                                                    <img src="<s:url value="/images/share.png"/>"/>
                                                    <span class="handtxt">转发</span>
                                                    <em><s:property
                                                            value="%{#request.numForwardMap[#weibo.weiboId]}"/></em>
                                                </span>
                                            </a>
                                        </li>
                                        <li class="curr">
                                            <a class="comment-a" href="javascript:void(0);" title="评论">
                                                <span class="spa">
                                                    <img src="<s:url value="/images/Dialog.png" />"/>
                                                    <span class="handtxt">评论</span>
                                                    <em><s:property
                                                            value="%{#request.commentNumMap[#weibo.weiboId]}"/></em>
                                                </span>
                                            </a>
                                        </li>
                                        <li class="thumb">
                                            <s:if test="%{#session.user!=null && #request.thumbMap[#weibo.weiboId]}">
                                                <%--已被赞--%>
                                                <a class="thumbed" title="已赞"
                                                   data-wid="<s:property value="#weibo.weiboId" />">
                                                    <span class="spa"><img
                                                            src="<s:url value="/images/up.png" />"/>
                                                        <span class="handtxt">已赞</span>
                                                        <em><s:property
                                                                value="%{#request.numThumbMap[#weibo.weiboId]}"/></em>
                                                    </span>
                                                </a>
                                            </s:if>
                                            <s:else>
                                                <a class="thumbing" href="javascript:void(0);" title="赞"
                                                   data-wid="<s:property value="#weibo.weiboId" />">
                                                    <span class="spa"><img
                                                            src="<s:url value="/images/up.png" />"/>
                                                        <span class="handtxt">赞</span>
                                                        <em><s:property
                                                                value="%{#request.numThumbMap[#weibo.weiboId]}"/></em>
                                                    </span>
                                                </a>
                                            </s:else>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <!--点赞评论区域结束-->
                            <!--互动评论区域display:none--->
                            <div class="handle_comt clearfix" id="handle_comt_2" style="display:none">
                                <div class="comt_list">
                                    <div class="comt_publish">
                                        <!---发布信息框-->
                                        <div class="WB_publish clearfix">
                                            <div class="p_input">
                                                <textarea class="W_input" action-type="check" cols=""
                                                          rows=""></textarea>
                                            </div>
                                            <div class="p_opt">
                                                <input type="submit" class="p_opt_btn" value="评论"/>
                                            </div>
                                        </div>
                                    </div>
                                    <!--发布框结束-->
                                    <div class="repeat_list">
                                        <s:iterator value="%{#request.commentMap[#weibo.weiboId]}" id="comment">
                                            <!--评论内容外框架-->
                                            <div class="list_box">
                                                <!--评论内容内框架-->
                                                <div class="list_li clearfix">
                                                    <div class="WB_face">
                                                        <img src="<s:url
                                                        value="/images/WB_frame_content/userPic.jpg"/>"/>
                                                    </div>
                                                    <div class="list_con clearfix">
                                                        <span class="WB_username">
                                                            <s:property value="%{#request.idMap[#comment.userId]}"/>
                                                        </span>
                                                        <!--用户名-->
                                                        <br/>
                                                        <p class="repeat_text">
                                                            <s:property value="#comment.cContent"/>
                                                        </p>
                                                        <!--评论的内容-->
                                                    </div>
                                                </div>
                                            </div>
                                        </s:iterator>
                                    </div>
                                    <!--用户评论内容区结束-->
                                </div>
                            </div>
                            <!--互动评论区结束-->
                        </div>
                        <!--单条微博结束-->
                    </s:else>
                    <!--单条微博结束-->
                </s:iterator>
                <div class="change_pages">
                    <%--上一页--%>
                    <s:if test="%{#request.page.hasPrePage}">
                            <span class="enabled">
                                <a href="<s:url value="/index?begin=%{#request.page.currentPage-1}&total=10" />">上一页</a>
                            </span>
                    </s:if>
                    <s:else>
                            <span>
                                <a>上一页</a>
                            </span>
                    </s:else>
                    <%--下一页--%>
                    <s:if test="%{#request.page.hasNextPage}">
                            <span class="enabled">
                                <a href="<s:url value="/index?begin=%{#request.page.currentPage+1}&total=10" />">下一页</a>
                            </span>
                    </s:if>
                    <s:else>
                            <span>
                                <a>下一页</a>
                            </span>
                    </s:else>
                </div>
                <!-- 分页结束 -->
            </div>
            <s:if test="#session.user!=null">
            <div class="WB_frame_b clearfix">
                <!--微博主体右侧推荐的内容-->
                <div class="WB_frameb">
                    <ul class="tools_right">
                        <li class="to_myindex">
                            <a href="${loginIndex_url}">我的首页</a>
                        </li>
                        <li class="logout">
                            <a href="${logout_url}">注销</a>
                        </li>
                    </ul>
                    <%--<!--第 1 个右侧浮窗-->--%>
                    <%--<div class="b_tuijian">--%>
                        <%--<div class="tuijian_title">--%>
                            <%--<h4>微博推荐</h4>--%>
                        <%--</div>--%>
                        <%--<div class="tuijian_cont">--%>
                            <%--<div class="tuijian_inner">--%>
                                <%--<ul>--%>
                                    <%--<li><a href="" title="完美特工明日上映" class="txt1">完美特工明日上映</a></li>--%>
                                    <%--<li><a href="" title="完美特工明日上映" class="txt1">完美特工明日上映</a></li>--%>
                                    <%--<li><a href="" title="完美特工明日上映" class="txt1">完美特工明日上映</a></li>--%>
                                    <%--<li><a href="" title="完美特工明日上映" class="txt1">完美特工明日上映</a></li>--%>
                                    <%--<li><a href="" title="完美特工明日上映" class="txt1">完美特工明日上映</a></li>--%>
                                    <%--<li><a href="" title="完美特工明日上映" class="txt1">完美特工明日上映</a></li>--%>
                                    <%--<li><a href="" title="完美特工明日上映" class="txt1">完美特工明日上映</a></li>--%>
                                <%--</ul>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                </div>
                <%--<div class="WB_frameb">--%>
                    <%--<!--第 2 个右侧浮窗-->--%>
                    <%--<div class="b_tuijian">--%>
                        <%--<div class="tuijian_title">--%>
                            <%--<h4>微博找人</h4>--%>
                        <%--</div>--%>
                        <%--<div class="tuijian_cont">--%>
                            <%--<div class="tuijian_inner">--%>
                                <%--<ul>--%>
                                    <%--<li>1</li>--%>
                                    <%--<li>2</li>--%>
                                    <%--<li>3</li>--%>
                                    <%--<li>4</li>--%>
                                    <%--<li>5</li>--%>
                                    <%--<li>6</li>--%>
                                    <%--<li><a href="" title="完美特工明日上映" class="txt1">完美特工明日上映</a></li>--%>
                                <%--</ul>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            </div>
            </s:if>
        </div>
        <!---返回顶部-->
        <div id="backtop" style="display: block;">
            <a href="#top"></a>
        </div>
        <div id="b_foot"></div>
        <!---底部footer开始-->
        <div class="footer">
            <div class="footer_link clearfix">
                <dl class="list">
                    <dt>合作&推荐</dt>
                    <dd>联系邮箱：bragg_chen@163.com</dd>
                    <dd><a href="http://www.baidu.com/" target="_blank">百度搜索</a></dd>
                </dl>
                <dl class="list">
                    <dt>微博帮助</dt>
                    <dd><a href="http://www.baidu.com/" target="_blank">意见反馈</a></dd>
                    <dd><a href="http://www.baidu.com/" target="_blank">常见问题</a></dd>
                </dl>
                <dd><a target="_blank" href="http://weibo.com/aj/static/jicp.html?_wv=6">京ICP证100780号</a></dd>
                <dd><a href="http://www.miibeian.gov.cn" target="__blank">京ICP备12002058号</a></dd>
            </div>
            <div class="footer_link2">
                <span>Copyright © 2009-2016 WEIBO 常熟理工学院软件132班J2EE</span>
                <a target="_blank"
                   href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=11000002000019"><img
                        src="<s:url value="/images/gongan.jpg" />"/>京公网安备11000002000019号</a>
            </div>
        </div>
        <!---底部信息栏结束-->
    </div>
    <!--main外结构结束-->
    <div class="box-forward">
        <div class="forward-header">
            <h3>转发微博</h3>
            <a href="javascript:void(0);" class="box-close">X</a>
        </div>
        <div class="forward-content">
            <div class=""></div>
            <textarea name="content"></textarea>
        </div>
        <div class="forward-footer">
            <button type="button" id="forward-sub">转发</button>
        </div>
    </div>
    <div class="overlay"></div>
</div>
<!--WB_main结束 主体内容结束-->
<script src="<s:url value="/js/jquery-1.12.4.min.js" />"></script>
<script src="<s:url value="/js/index-TOP.js"/>"></script>
<script src="<s:url value="/js/comment.js"/> "></script>
<script src="<s:url value="/js/thumb.js" />"></script>
<script src="<s:url value="/js/forward.js"/> "></script>
<script>
    thumb_init('${thumbing_url}');
    comment_init('${comment_url}');
    forward_init('${forward_url}');
</script>
</body>

</html>
