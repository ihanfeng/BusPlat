package me.vliupro.smb.action;

import com.opensymphony.xwork2.ActionSupport;
import me.vliupro.smb.po.Comment;
import me.vliupro.smb.po.User;
import me.vliupro.smb.po.Weibo;
import me.vliupro.smb.service.*;
import me.vliupro.smb.utils.Page;
import org.apache.struts2.ServletActionContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vliupro on 16-6-16.
 */
public class OthersAction extends ActionSupport {

    private WeiboService ws = null;
    private UserService us = null;
    private FollowService fs = null;
    private ThumbService ts = null;
    private CommentService cs = null;

    private String begin;
    private String total;
    private String userId;

    public OthersAction() {
        ws = new WeiboServiceImpl();
        us = new UserServiceImpl();
        fs = new FollowServiceImpl();
        ts = new ThumbServiceImpl();
        cs = new CommentServiceImpl();
    }

    public String others() {
        if (begin == null || total == null) {
            begin = "1";
            total = "10";
        }
        User sessionUser = new User();
        sessionUser.mapToUser((Map<String, Object>) ServletActionContext.getRequest().getSession().getAttribute("user"));
        User user = us.getUserById(Integer.parseInt(userId));
        //取出userId的关注的人数、关注自己的人数、已发的微博数、是否能赞
        Map<String, Object> infoMap = new HashMap<>();
        infoMap.put("numFollowing", fs.getFollowerTotal(Integer.parseInt(userId)));
        infoMap.put("numFollow", fs.getFollowedTotal(Integer.parseInt(userId)));
        infoMap.put("numWeibo", ws.getNumIfUserWeibo(Integer.parseInt(userId)));
        infoMap.put("isFollowed", fs.checkFollow(sessionUser.getUserId(), Integer.parseInt(userId)));
        List<Integer> users = new ArrayList<>();
        users.add(user.getUserId());
        Page<Weibo> page = ws.getWeibosByListUserIds(users, Integer.parseInt(begin), Integer.parseInt(total));
        //根据Page里面的items中每个weibo的userId获取username后，以userId为键，username为值，存入HashMap，放入request
        Map<Integer, String> usersMap = new HashMap<>();
        Map<Integer, Boolean> thumbMap = new HashMap<>();
        Map<Integer, Integer> numThumbMap = new HashMap<>();
        Map<Integer, Object> originUserMap = new HashMap<>();
        List<Integer> weiboIds = new ArrayList<>();
        //每个微博评论数量
        Map<Integer, Integer> commentNumMap = new HashMap<>();
        //每个微博下的评论
        Map<Integer, Object> commentMap = new HashMap<>();
        //评论对应的用户名
        for (Weibo weibo : page.getItems()) {
            //userId对应username放入userMap
            usersMap.put(weibo.getUserId(), us.getUserById(weibo.getUserId()).getNickName());
            if (!weibo.isOriginal()) {
                Weibo originW = ws.getWeiboById(weibo.getOriginId());
                User originU = us.getUserById(originW.getUserId());
                originUserMap.put(weibo.getWeiboId(), originU.getNickName());
            }
            //微博是否能被session用户赞存入thumbMap
            thumbMap.put(weibo.getWeiboId(), ts.isThumbed(sessionUser.getUserId(), weibo.getWeiboId()));
            //微博的赞的数量
            numThumbMap.put(weibo.getWeiboId(), ts.thumbNumOfWeibo(weibo.getWeiboId()));
            //微博转发数量
            weiboIds.add(weibo.getWeiboId());
            //每个微博评论数量
            commentNumMap.put(weibo.getWeiboId(), cs.commentNumOfWeibo(weibo.getWeiboId()));
            List<Comment> comments = new ArrayList<>();
            comments = cs.getCommentsByWeibo(weibo.getWeiboId(), 0, 10).getItems();
            //每个微博下的评论
            commentMap.put(weibo.getWeiboId(), comments);
            for (Comment comment : comments) {
                usersMap.put(comment.getUserId(), us.getUserById(comment.getUserId()).getNickName());
            }
        }
        Map<String, Integer> numForward = ws.getNumOfForwardWeibo(weiboIds);

        ServletActionContext.getRequest().setAttribute("infoMap", infoMap);
        ServletActionContext.getRequest().setAttribute("p_user", user);
        ServletActionContext.getRequest().setAttribute("page", page);
        ServletActionContext.getRequest().setAttribute("idMap", usersMap);
        ServletActionContext.getRequest().setAttribute("infoMap", infoMap);
        ServletActionContext.getRequest().setAttribute("thumbMap", thumbMap);
        ServletActionContext.getRequest().setAttribute("numThumbMap", numThumbMap);
        ServletActionContext.getRequest().setAttribute("numForwardMap", numForward);
        ServletActionContext.getRequest().setAttribute("originUserMap", originUserMap);
        ServletActionContext.getRequest().setAttribute("commentNumMap", commentNumMap);
        ServletActionContext.getRequest().setAttribute("commentMap", commentMap);
        return SUCCESS;
    }


    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
