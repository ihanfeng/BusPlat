<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: vliupro
  Date: 16-6-16
  Time: 下午4:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<s:url action="api/thumbing" var="thumbing_url"/>
<s:url action="api/comment" var="comment_url"/>
<s:url action="forward" var="forward_url"/>
<s:url action="api/follow" var="follow_url"/>
<s:url action="api/unfollow" var="unfollow_url"/>
<s:url action="loginIndex" var="loginIndex_url"/>
<html>

<head>
    <meta charset="utf-8">
    <title><s:property value="#request.p_user.nickName" /></title>
    <link rel="stylesheet" type="text/css" href="<s:url value="/css/personal.css"/>" />
    <link rel="stylesheet" type="text/css" href="<s:url value="/css/index.css"/>" />
    <link rel="stylesheet" type="text/css" href="<s:url value="/css/myindex.css"/>" />

</head>

<body id="personal">
<div class="WB_myindex">
    <div class="WB_mymain">
        <div class="blog_top">
            <!--顶部导航栏-->
            <div class="top_inner clearfix">
                <div class="top_logo">
                    <a href="${loginIndex_url}"><img src="<s:url value="/images/logo2.png" /> "/><em><h2 class="logo_t">微博</h2></em></a>
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
                            <a href="/loginIndex" title="首页">
                                <img src="<s:url value="/images/home.png"/> " class="list_img" name="img0" onmousemove="color()" />
                                <span>首页</span>
                            </a>
                        </li>
                        <li>
                            <a href="personal.html" title="">
                                <img src="<s:url value="/images/person.png"/>" class="list_img" id="list_img" />
                                <span class="nav_span1"><s:property value="#request.p_user.nickName" /></span>
                            </a>
                        </li>
                        <li>|</li>
                        <li>
                            <a href="/logout" onclick="">退出</a>
                        </li>
                    </ul>
                </div>
                <!--消息提醒和个人-->
            </div>
        </div>
        <!--顶部导航栏到此结束-->
        <!--中间主要内容-->
        <div class="per_main">
            <div class="per_main_head clearfix">
                <!-- 个人主体内容的上半部分-->
                <div class="per_userPic">
                    <div>
                        <a href=""> <img src="<s:url value="/images/WB_frame_content/userPic.jpg"/>" /> </a>
                    </div>
                    <div class="follow">
                        <!--在其他人首页的关注按钮-->
                        <p><s:property value="#request.p_user.nickName" /></p>
                        <!--用户名，不加链接-->
                        <br />
                        <p>
                            <s:if test="#request.infoMap.isFollowed">
                                <input type="button" class="foll" id="foll" value="取消关注" data-isf="-1"
                                       data-uid="<s:property value="#request.p_user.userId"/>" />
                            </s:if>
                            <s:else>
                                <input type="button" class="foll" id="foll" value="添加关注" data-isf="1"
                                       data-uid="<s:property value="#request.p_user.userId"/>" />
                            </s:else>
                        </p>
                    </div>
                </div>
                <%--<div class="menu_box">--%>
                    <%--<ul class="menu">--%>
                        <%--<li><a href="#"> 主 页 </a></li>--%>
                        <%--<li><a href="#"> 关注的人 </a></li>--%>
                        <%--<li><a href="others-information.html">个人资料</a></li>--%>
                    <%--</ul>--%>
                <%--</div>--%>
            </div>
            <div class="per_main_content">
                <!--个人主体部分的下半部分-->
                <div class="per_left">
                    <!--左边个人资料卡、导航栏（包括：我的资料，我的消息-->
                    <div class="perinfo_detail clearfix">
                        <ul>
                            <li class="perinfo_txt1">
                                <a href="#"><strong>关注</strong><br />
                                    <span class="info_txt2">
                                        <s:property value="#request.infoMap.numFollowing"/>
                                    </span></a>
                            </li>
                            <li class="perinfo_txt1">
                                <a href=""><strong>粉丝</strong><br />
                                    <span class="info_txt2">
                                        <s:property value="#request.infoMap.numFollow"/>
                                    </span></a>
                            </li>
                            <li>
                                <a href=""><strong>微博</strong><br />
                                    <span class="info_txt2">
                                        <s:property value="#request.infoMap.numWeibo"/>
                                    </span></a>
                            </li>
                        </ul>
                    </div>
                    <%--<div class="infomation" id="information">--%>
                        <%--<div class="hd">--%>
                            <%--<h4><a href="others-information.html">个人档</a></h4>--%>
                        <%--</div>--%>
                        <%--<div class="bd">--%>
                            <%--<ul>--%>
                                <%--<li>--%>
                                    <%--<div class="ll">性别：</div>--%>
                                    <%--<!--类名为小写的L和R表示左和右-->--%>
                                    <%--<div class="rr">男</div>--%>
                                <%--</li>--%>
                                <%--<li>--%>
                                    <%--<div class="ll">年龄：</div>--%>
                                    <%--<div class="rr">22</div>--%>
                                <%--</li>--%>
                                <%--<li>--%>
                                    <%--<div class="ll">家乡：</div>--%>
                                    <%--<div class="rr">江苏省 宿迁市</div>--%>
                                <%--</li>--%>
                                <%--<li class="li_bottom">--%>
                                    <%--<div class="ll">公司：</div>--%>
                                    <%--<div class="rr">常熟理工学院</div>--%>
                                <%--</li>--%>
                            <%--</ul>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                </div>
                <!--左边个人资料卡、导航栏（包括：我的资料，我的消息 -----------结束-->
                <div class="per_right clearfix">
                    <!--右边个人发布的微博、消息显示-->
                    <div class="per_right_inner">
                        <!--每一条微博的格式-->
                        <s:iterator value="%{#request.page.items}" id="weibo">
                            <s:if test="%{#weibo.original}">
                                <!--每一条微博的格式-->
                                <div class="WB_frame_content">
                                    <div class="content_detail clearfix">
                                        <!--微博详情-->
                                        <div class="user_info">
                                            <!--用户信息-->
                                            <div class="userPic">
                                                <a href="" target="_blank" title=""><img
                                                        src="<s:url value="/images/WB_frame_content/userPic.jpg" />"
                                                        title=""/></a>
                                            </div>
                                            <div class="userName">
                                                <a href="<s:url value="" />" target="_blank" title="" class="name"><s:property
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
                                                    <s:if test="%{#request.thumbMap[#weibo.weiboId]}">
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
                                    <div class="handle_comt clearfix" id="handle_comt_1" style="display:none">
                                        <div class="comt_list">
                                            <div class="comt_publish">
                                                <!---发布信息框-->
                                                <div class="WB_publish clearfix"
                                                     data-wid="<s:property value="#weibo.weiboId"/>">
                                                    <div class="p_input">
                                                            <textarea class="W_input" action-type="check" cols=""
                                                                      rows=""></textarea>
                                                    </div>
                                                    <div class="p_opt">
                                                        <input type="button" class="p_opt_btn" value="评论"/>
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
                                                                <img src="<s:url value="/images/WB_frame_content/userPic.jpg"/>"/>
                                                            </div>
                                                            <div class="list_con clearfix">
                                                                <span class="WB_username">
                                                                    <s:property value="%{#request.idMap[#comment.userId]}"/>
                                                                </span>
                                                                <!--用户名-->
                                                                <time style="font-size: 12px;">
                                                                    <s:date name="#comment.cCtime" format="yyyy-MM-dd HH:mm:ss"/>
                                                                </time>
                                                                <br/>
                                                                <p class="repeat_text">
                                                                    <s:property value="#comment.cContent"/>
                                                                </p>
                                                                <!--评论的内容-->
                                                            </div>
                                                        </div>
                                                    </div>
                                                </s:iterator>
                                                <!--用户评论内容区结束-->
                                            </div>
                                        </div>
                                        <!--互动评论区结束-->
                                    </div>
                                    <!--单条微博结束-->
                                </div>
                            </s:if>
                            <s:else>
                                <!--每一条 “转发微博” 微博的格式-->
                                <div class="WB_frame_content">
                                    <div class="content_detail clearfix">
                                        <!--微博详情-->
                                        <div class="user_info clearfix">
                                            <!--用户信息-->
                                            <div class="userPic">
                                                <a href="" target="_blank" title=""><img
                                                        src="<s:url value="/images/WB_frame_content/userPic.jpg"/>" title=""/></a>
                                            </div>
                                            <div class="userName">
                                                <a href="" target="_blank" title="" class="name">
                                                    <s:property value="#request.idMap[#weibo.userId]"/>
                                                </a>
                                                <p>
                                                    <s:date name="#weibo.wFtime" format="yyyy-MM-dd HH:mm:ss"/>
                                                </p>
                                            </div>
                                        </div>
                                        <!--转发的原创者微博内容-->
                                        <div class="discuss"><s:property value="#weibo.remark"/></div>
                                        <div class="resend">
                                            <!--转发者的评论--当没有评论内容的时候，系统添加“转发微博1”-->
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
                                                <%--<div class="WB_media">--%>
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
                                                    <s:if test="%{#request.thumbMap[#weibo.weiboId]}">
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
                                                <div class="WB_publish clearfix"
                                                     data-wid="<s:property value="#weibo.weiboId"/>">
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
                                                                <img src="<s:url value="/images/WB_frame_content/userPic.jpg"/>"/>
                                                            </div>
                                                            <div class="list_con clearfix">
                                                                <span class="WB_username">
                                                                    <s:property value="%{#request.idMap[#comment.userId]}"/>
                                                                </span>
                                                                <time>
                                                                    <s:date name="#comment.cCtime" format="yyyy-MM-dd HH:mm:ss"/>
                                                                </time>
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
                        </s:iterator>
                    </div>
                </div>
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
        <a target="_blank" href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=11000002000019">
            <img src="<s:url value="/images/gongan.jpg"/>" />京公网安备11000002000019号
        </a>
    </div>
</div>
<div class="box-forward" style="position: fixed">
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
<!---底部信息栏结束-->
<script src="<s:url value="/js/myindex.js"/>"></script>
<script src="<s:url value="/js/jquery-1.12.4.min.js"/>"></script>
<script src="<s:url value="/js/comment.js"/> "></script>
<script src="<s:url value="/js/thumb.js" />"></script>
<script src="<s:url value="/js/forward.js"/> "></script>
<script src="<s:url value="/js/follow.js"/>"></script>
<script>
    thumb_init('${thumbing_url}');
    comment_init('${comment_url}');
    forward_init('${forward_url}');
    follow_init('${follow_url}', '${unfollow_url}');
</script>

</body>

</html>
