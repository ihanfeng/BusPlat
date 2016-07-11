package me.vliupro.smb.action;

import com.opensymphony.xwork2.ActionSupport;
import me.vliupro.smb.utils.SecurityCode;
import me.vliupro.smb.utils.SecurityImage;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import java.io.ByteArrayInputStream;
import java.util.Map;

/**
 * Created by vliupro on 16-6-1.
 */
public class SecurityAction extends ActionSupport implements SessionAware {

    private Map<String, Object> session;

    //图片流
    private ByteArrayInputStream imageStream;

    private String timestamp;

    public String execute() {
        String securityCode = SecurityCode.getSecurityCode();
        imageStream = SecurityImage.getImageAsInputStream(securityCode);
        this.session.put("SESSION_SECURITY_CODE", securityCode);
        return SUCCESS;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public ByteArrayInputStream getImageStream() {
        return imageStream;
    }

    public void setImageStream(ByteArrayInputStream imageStream) {
        this.imageStream = imageStream;
    }
}
