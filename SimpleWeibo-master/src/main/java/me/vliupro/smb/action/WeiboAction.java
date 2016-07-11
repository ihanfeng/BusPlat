package me.vliupro.smb.action;

import com.opensymphony.xwork2.ActionSupport;
import me.vliupro.smb.po.Weibo;
import me.vliupro.smb.service.WeiboService;
import me.vliupro.smb.service.WeiboServiceImpl;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Date;
import java.util.Map;

/**
 * Created by vliupro on 16-5-24.
 */
public class WeiboAction extends ActionSupport implements SessionAware {

    private WeiboService ws;

    public WeiboAction() {
        ws = new WeiboServiceImpl();
    }

    Map<String, Object> session;

    public String content;
    public String weiboId;
    public String remark;

    /**
     * 发布微博
     * @return
     */
    public String publish() {
        if (content == null || content.equals("")) {
            return ERROR;
        }
        Map<String, Object> userMap = (Map<String, Object>) this.session.get("user");
        //判断session中是否存在user对象，存在就允许发微博
        if (userMap != null) {
            Weibo weibo = new Weibo();
            weibo.setwContent(content);
            weibo.setUserId(Integer.parseInt(userMap.get("id").toString()));
            weibo.setwCtime(new Date());
            weibo.setOriginal(true);
            weibo.setOriginId(-1);
            weibo.setRemark("");
            weibo.setwFtime(null);
            if (ws.publish(weibo)) {
                return SUCCESS;
            } else {
                return ERROR;
            }
        } else {
            return ERROR;
        }
    }

    /**
     * 转发微博
     * @return
     */
    public String forward() {
        if (weiboId == null || weiboId.equals("")) {
            return ERROR;
        }
        if (remark == null) {
            remark = "转发微博";
        }
        Map<String, Object> user = (Map<String, Object>) session.get("user");
        if (user != null) {
            //获取待转发的原微博，修改微博forwardId，是否原创，转发时间， 转发
            Weibo weibo = ws.getWeiboById(Integer.parseInt(weiboId));
            weibo.setOriginal(false);
            weibo.setUserId(Integer.parseInt(user.get("id").toString()));
            weibo.setRemark(remark);
            weibo.setOriginId(weibo.getWeiboId());
            weibo.setwFtime(new Date());
            if (ws.publish(weibo)) {
                return SUCCESS;
            } else {
                return ERROR;
            }
        } else {
            return ERROR;
        }
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}
