package me.vliupro.smb.action;

import com.opensymphony.xwork2.ActionSupport;
import me.vliupro.smb.po.Thumb;
import me.vliupro.smb.po.User;
import me.vliupro.smb.service.ThumbService;
import me.vliupro.smb.service.ThumbServiceImpl;
import org.apache.struts2.interceptor.SessionAware;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vliupro on 16-6-3.
 */
public class ThumbAction extends ActionSupport implements SessionAware {

    private Map<String, Object> jsonMap;

    private ThumbService ts;
    private Map<String, Object> session;

    private String weiboId;

    public ThumbAction() {
        ts = new ThumbServiceImpl();
        jsonMap = new HashMap<>();
    }

    /**
     * 点赞
     *
     * @return
     */
    public String thumbing() {
        User user = new User();
        if (this.session.get("user") != null && weiboId != null) {
            user.mapToUser((Map<String, Object>) this.session.get("user"));
            jsonMap.put("login_in", true);
            jsonMap.put("url_err", false);
            if (ts.thumbing(new Thumb(user.getUserId(), Integer.parseInt(weiboId)))) {
                int num = ts.thumbNumOfWeibo(Integer.parseInt(weiboId));
                jsonMap.put("status", 1);
                jsonMap.put("thumb_num", num);
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

    /**
     * 取消点赞
     *
     * @return
     */
    public String unThumb() {
        User user = new User();
        if (this.session.get("user") != null && weiboId != null) {
            user.mapToUser((Map<String, Object>) this.session.get("user"));
            jsonMap.put("login_in", true);
            jsonMap.put("url_err", false);
            if (ts.unThumb(user.getUserId(), Integer.parseInt(weiboId))) {
                int num = ts.thumbNumOfWeibo(Integer.parseInt(weiboId));
                jsonMap.put("status", 1);
                jsonMap.put("thumb_num", num);
            } else {
                jsonMap.put("status", 0);
            }
        } else {
            jsonMap.put("status", 0);
            if (weiboId == null) {
                jsonMap.put("url_err", true);
                jsonMap.put("login_in", true);
            } else {
                jsonMap.put("login_in", false);
                jsonMap.put("url_err", false);
            }
        }
        return SUCCESS;
    }

    public Map<String, Object> getJsonMap() {
        return jsonMap;
    }

    public void setJsonMap(Map<String, Object> jsonMap) {
        this.jsonMap = jsonMap;
    }

    public String getWeiboId() {
        return weiboId;
    }

    public void setWeiboId(String weiboId) {
        this.weiboId = weiboId;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}
