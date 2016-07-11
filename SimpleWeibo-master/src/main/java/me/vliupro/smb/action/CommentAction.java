package me.vliupro.smb.action;

import com.opensymphony.xwork2.ActionSupport;
import me.vliupro.smb.po.Comment;
import me.vliupro.smb.po.User;
import me.vliupro.smb.service.CommentService;
import me.vliupro.smb.service.CommentServiceImpl;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vliupro on 16-6-6.
 */
public class CommentAction extends ActionSupport implements SessionAware {

    private Map<String, Object> session;

    private Map<String, Object> jsonMap;

    private CommentService cs;

    public CommentAction() {
        jsonMap = new HashMap<>();
        cs = new CommentServiceImpl();
    }

    private String content;
    private String weiboId;

    public String addComment() {
        User user = new User();
        if (this.session.get("user") != null && weiboId != null && content != null) {
            user.mapToUser((Map<String, Object>) this.session.get("user"));
            jsonMap.put("login_in", true);
            jsonMap.put("url_err", false);
            Comment comment = new Comment();
            comment.setcContent(content);
            comment.setcCtime(new Date());
            comment.setUserId(user.getUserId());
            comment.setWeiboId(Integer.parseInt(weiboId));
            if (cs.pubComment(comment)){
                int num = cs.commentNumOfWeibo(Integer.parseInt(weiboId));
                jsonMap.put("status", 1);
                jsonMap.put("comment_num", num);
                jsonMap.put("comment", comment);
            } else {
                jsonMap.put("status", 0);
            }
        } else {
            jsonMap.put("status", 0);
            if (this.session.get("user") != null) {
                jsonMap.put("login_in", true);
            } else {
                jsonMap.put("login_in", false);
                if (weiboId == null) {
                    jsonMap.put("url_err", true);
                } else {
                    jsonMap.put("url_err", false);
                }
            }
        }
        return SUCCESS;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public Map<String, Object> getJsonMap() {
        return jsonMap;
    }

    public void setJsonMap(Map<String, Object> jsonMap) {
        this.jsonMap = jsonMap;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWeiboId() {
        return weiboId;
    }

    public void setWeiboId(String weiboId) {
        this.weiboId = weiboId;
    }
}
