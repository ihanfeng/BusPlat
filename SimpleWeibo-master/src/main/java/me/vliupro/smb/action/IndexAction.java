package me.vliupro.smb.action;

import com.opensymphony.xwork2.ActionSupport;
import me.vliupro.smb.po.Comment;
import me.vliupro.smb.po.User;
import me.vliupro.smb.po.Weibo;
import me.vliupro.smb.service.*;
import me.vliupro.smb.utils.Page;
import org.apache.struts2.ServletActionContext;

import java.util.*;

/**
 * Created by vliupro on 16-5-30.
 */
public class IndexAction extends ActionSupport {

    private WeiboService ws = null;
    private UserService us = null;
    private FollowService fs = null;
    private ThumbService ts = null;
    private CommentService cs = null;

    private String begin;
    private String total;

    private String key;

    public IndexAction() {
        ws = new WeiboServiceImpl();
        us = new UserServiceImpl();
        fs = new FollowServiceImpl();
        ts = new ThumbServiceImpl();
        cs = new CommentServiceImpl();
    }

    /**
     * 转发到index
     * @return
     */
    public String noLoginIndex() {
        if (begin == null || total == null) {
            begin = "1";
            total = "10";
        }
        User user = null;
        Map<Integer, Boolean> thumbMap = null;
        Map<String, Object> map = (Map<String, Object>) ServletActionContext.getRequest().getSession().getAttribute("user");
        if (map != null) {
            user = new User();
            user.mapToUser(map);
        }
        //取出session中的user信息存入User对象
        Map<Integer, String> usersMap = new HashMap<>();
        Page<Weibo> page = ws.getWeibosByPage(Integer.parseInt(begin), Integer.parseInt(total));
        List<Weibo> weibos = page.getItems();
        List<Integer> weiboIds = new ArrayList<>();
        Map<Integer, Object> originUserMap = new HashMap<>();
        if (user != null) {
            thumbMap = new HashMap<>();
        }
        Map<Integer, Integer> numThumbMap = new HashMap<>();
        //每个微博评论数量
        Map<Integer, Integer> commentNumMap = new HashMap<>();
        //每个微博下的评论
        Map<Integer, Object> commentMap = new HashMap<>();
        for (Weibo weibo : weibos) {
            usersMap.put(weibo.getUserId(), us.getUserById(weibo.getUserId()).getNickName());
            if (!weibo.isOriginal()) {
                Weibo originW = ws.getWeiboById(weibo.getOriginId());
                User originU = us.getUserById(originW.getUserId());
                originUserMap.put(weibo.getWeiboId(), originU.getNickName());
            }
            if (user != null) {
                //微博是否能被session用户赞存入thumbMap
                thumbMap.put(weibo.getWeiboId(), ts.isThumbed(user.getUserId(), weibo.getWeiboId()));
            }
            //微博的赞的数量
            numThumbMap.put(weibo.getWeiboId(), ts.thumbNumOfWeibo(weibo.getWeiboId()));
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
        ServletActionContext.getRequest().setAttribute("page", page);
        ServletActionContext.getRequest().setAttribute("idMap", usersMap);
        ServletActionContext.getRequest().setAttribute("numThumbMap", numThumbMap);
        ServletActionContext.getRequest().setAttribute("numForwardMap", numForward);
        ServletActionContext.getRequest().setAttribute("thumbMap", thumbMap);
        ServletActionContext.getRequest().setAttribute("originUserMap", originUserMap);
        ServletActionContext.getRequest().setAttribute("commentNumMap", commentNumMap);
        ServletActionContext.getRequest().setAttribute("commentMap", commentMap);
        return SUCCESS;
    }

    /**
     * 转发到loginIndex
     * @return
     */
    public String loginIndex() {
        if (begin == null || total == null) {
            begin = "1";
            total = "10";
        }
        User user = new User();
        //取出session中的user信息存入User对象
        user.mapToUser((Map<String, Object>) ServletActionContext.getRequest().getSession().getAttribute("user"));
        //取出myindex所需要的内容
        Set<Integer> users = new HashSet<>();
        List<Integer> weiboIds = new ArrayList<>();
        users.add(user.getUserId());
        //由userId获取自己所关注人的ID+上自己的ID放入UserIdList，
        List<Integer> follows = fs.getFollowsByFollowerId(user.getUserId());
        users.addAll(follows);
        //然后根据UserIdList取出Weibo（根据时间排序，每次取total个）
        //以上的微博存入Page
        Page<Weibo> page = ws.getWeibosByListUserIds(new ArrayList<>(users), Integer.parseInt(begin), Integer.parseInt(total));
        //根据Page里面的items中每个weibo的userId获取username后，以userId为键，username为值，存入HashMap，放入request
        Map<Integer, String> usersMap = new HashMap<>();
        Map<Integer, Boolean> thumbMap = new HashMap<>();
        Map<Integer, Integer> numThumbMap = new HashMap<>();
        Map<Integer, Object> originUserMap = new HashMap<>();
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
            thumbMap.put(weibo.getWeiboId(), ts.isThumbed(user.getUserId(), weibo.getWeiboId()));
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
        //取出自己的关注的人数、关注自己的人数、已发的微博数、是否能赞
        Map<String, Integer> infoMap = new HashMap<>();
        infoMap.put("numFollowing", fs.getFollowerTotal(user.getUserId()));
        infoMap.put("numFollow", fs.getFollowedTotal(user.getUserId()));
        infoMap.put("numWeibo", ws.getNumIfUserWeibo(user.getUserId()));
        //放入转发数量
        Map<String, Integer> numForward = ws.getNumOfForwardWeibo(weiboIds);
        //Page放入request, 各个map放入request
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

    public String search() {
//        System.out.println("key = " + key);
        if (begin == null || total == null) {
            begin = "1";
            total = "10";
        }
        User user = new User();
        //取出session中的user信息存入User对象
        user.mapToUser((Map<String, Object>) ServletActionContext.getRequest().getSession().getAttribute("user"));
        //取出myindex所需要的内容
        List<Integer> weiboIds = new ArrayList<>();
        //然后根据UserIdList取出Weibo（根据时间排序，每次取total个）
        //以上的微博存入Page
        Page<Weibo> weiboPage = ws.searchWeibos(key.trim(), Integer.parseInt(begin), Integer.parseInt(total));
//        System.out.println("weiboPage.items: " + weiboPage.getItems());
        //根据Page里面的items中每个weibo的userId获取username后，以userId为键，username为值，存入HashMap，放入request
        Map<Integer, String> usersMap = new HashMap<>();
        Map<Integer, Boolean> thumbMap = new HashMap<>();
        Map<Integer, Integer> numThumbMap = new HashMap<>();
        Map<Integer, Object> originUserMap = new HashMap<>();
        //每个微博评论数量
        Map<Integer, Integer> commentNumMap = new HashMap<>();
        //每个微博下的评论
        Map<Integer, Object> commentMap = new HashMap<>();
        //评论对应的用户名
        for (Weibo weibo : weiboPage.getItems()) {
            //userId对应username放入userMap
            usersMap.put(weibo.getUserId(), us.getUserById(weibo.getUserId()).getNickName());
            if (!weibo.isOriginal()) {
                Weibo originW = ws.getWeiboById(weibo.getOriginId());
                User originU = us.getUserById(originW.getUserId());
                originUserMap.put(weibo.getWeiboId(), originU.getNickName());
            }
            //微博是否能被session用户赞存入thumbMap
            thumbMap.put(weibo.getWeiboId(), ts.isThumbed(user.getUserId(), weibo.getWeiboId()));
            //微博的赞的数量
            numThumbMap.put(weibo.getWeiboId(), ts.thumbNumOfWeibo(weibo.getWeiboId()));
            //微博转发数量
            weiboIds.add(weibo.getWeiboId());
            //每个微博评论数量
            commentNumMap.put(weibo.getWeiboId(), cs.commentNumOfWeibo(weibo.getWeiboId()));
            //评论数量暂时写死
            List<Comment> comments = cs.getCommentsByWeibo(weibo.getWeiboId(), 0, 10).getItems();
            //每个微博下的评论
            commentMap.put(weibo.getWeiboId(), comments);
            for (Comment comment : comments) {
                usersMap.put(comment.getUserId(), us.getUserById(comment.getUserId()).getNickName());
            }
        }
        //取出自己的关注的人数、关注自己的人数、已发的微博数、是否能赞
        Map<String, Integer> infoMap = new HashMap<>();
        infoMap.put("numFollowing", fs.getFollowerTotal(user.getUserId()));
        infoMap.put("numFollow", fs.getFollowedTotal(user.getUserId()));
        infoMap.put("numWeibo", ws.getNumIfUserWeibo(user.getUserId()));
        //放入转发数量
        Map<String, Integer> numForward = ws.getNumOfForwardWeibo(weiboIds);
        //Page放入request, 各个map放入request
        ServletActionContext.getRequest().setAttribute("page", weiboPage);
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


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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
}
