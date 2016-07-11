package me.vliupro.smb.action;

import com.opensymphony.xwork2.ActionSupport;
import me.vliupro.smb.po.Follow;
import me.vliupro.smb.po.User;
import me.vliupro.smb.service.FollowService;
import me.vliupro.smb.service.FollowServiceImpl;
import org.apache.struts2.interceptor.SessionAware;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vliupro on 16-6-16.
 */
public class FollowAction extends ActionSupport implements SessionAware {

    private Map<String, Object> session;

    private Map<String, Object> jsonMap;
    private FollowService fs = null;

    private String userId;

    public FollowAction() {
        fs = new FollowServiceImpl();
        jsonMap = new HashMap<>();
    }

    public String following() {
        User user = new User();
        if (this.session.get("user") != null && userId != null) {
            user.mapToUser((Map<String, Object>) this.session.get("user"));
            jsonMap.put("login_in", true);
            jsonMap.put("url_err", false);
            if (Integer.parseInt(userId) == user.getUserId()) {
                jsonMap.put("status", 0);
                jsonMap.put("error", "不能关注自己");
            } else {
                if (fs.checkFollow(user.getUserId(), Integer.parseInt(userId))) {
                    jsonMap.put("error", "不能多次关注");
                    jsonMap.put("status", 0);
                } else {
                    if (fs.follow(new Follow(user.getUserId(), Integer.parseInt(userId)))) {
                        jsonMap.put("status", 1);
                    } else {
                        jsonMap.put("status", 0);
                    }
                }
            }
        }else {
            jsonMap.put("status", 0);
            if (this.session.get("user") != null) {
                jsonMap.put("login_in", true);
            } else {
                jsonMap.put("login_in", false);
                if (userId == null) {
                    jsonMap.put("url_err", true);
                } else {
                    jsonMap.put("url_err", false);
                }
            }
        }
        return SUCCESS;
    }

    public String unfollow() {
        User user = new User();
        if (this.session.get("user") != null && userId != null) {
            user.mapToUser((Map<String, Object>) this.session.get("user"));
            jsonMap.put("login_in", true);
            jsonMap.put("url_err", false);
            if (Integer.parseInt(userId) == user.getUserId()) {
                jsonMap.put("status", 0);
                jsonMap.put("error", "不能取消关注自己");
            } else {
                if (!fs.checkFollow(user.getUserId(), Integer.parseInt(userId))) {
                    jsonMap.put("error", "关注不存在");
                    jsonMap.put("status", 0);
                } else {
                    if (fs.unFollow(new Follow(user.getUserId(), Integer.parseInt(userId)))) {
                        jsonMap.put("status", 1);
                    } else {
                        jsonMap.put("status", 0);
                    }
                }
            }
        }else {
            jsonMap.put("status", 0);
            if (this.session.get("user") != null) {
                jsonMap.put("login_in", true);
            } else {
                jsonMap.put("login_in", false);
                if (userId == null) {
                    jsonMap.put("url_err", true);
                } else {
                    jsonMap.put("url_err", false);
                }
            }
        }
        return SUCCESS;
    }

    public String checkFollow() {
        User user = new User();
        if (this.session.get("user") != null && userId != null) {
            user.mapToUser((Map<String, Object>) this.session.get("user"));
            jsonMap.put("login_in", true);
            jsonMap.put("url_err", false);
            if (!fs.checkFollow(user.getUserId(), Integer.parseInt(userId))) {
                jsonMap.put("isFollowed", false);
            } else {
                jsonMap.put("isFollowed", true);
            }
        } else {
            if (this.session.get("user") != null) {
                jsonMap.put("login_in", true);
            } else {
                jsonMap.put("login_in", false);
                if (userId == null) {
                    jsonMap.put("url_err", true);
                } else {
                    jsonMap.put("url_err", false);
                }
            }
        }
        return SUCCESS;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Map<String, Object> getJsonMap() {
        return jsonMap;
    }

    public void setJsonMap(Map<String, Object> jsonMap) {
        this.jsonMap = jsonMap;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}
