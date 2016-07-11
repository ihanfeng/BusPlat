package me.vliupro.smb.action;

import com.opensymphony.xwork2.ActionSupport;
import me.vliupro.smb.service.UserService;
import me.vliupro.smb.service.UserServiceImpl;
import org.apache.struts2.ServletActionContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vliupro on 16-6-2.
 */
public class CheckAction extends ActionSupport {

    private Map<String, Object> jsonMap;

    private UserService us;

    private String email;
    private String username;
    private String securityCode;

    public CheckAction() {
        us = new UserServiceImpl();
        jsonMap = new HashMap<>();
    }

    public String checkEmail() {
        if (email != null && us.checkEmail(email)) {
            jsonMap.put("status", 1);
        } else {
            jsonMap.put("status", 0);
        }
        return SUCCESS;
    }

    public String checkUsername() {
        if (username != null && us.checkNickName(username)) {
            jsonMap.put("status", 1);
        } else {
            jsonMap.put("status", 0);
        }
        return SUCCESS;
    }

    public String checkSecurityCode() {
        String sessionSecurityCode = (String) ServletActionContext.getRequest().getSession().getAttribute("SESSION_SECURITY_CODE");
        if (securityCode != null && securityCode.equals(sessionSecurityCode)) {
            jsonMap.put("status", 1);
        } else {
            jsonMap.put("status", 0);
        }
        return SUCCESS;
    }

    public Map<String, Object> getJsonMap() {
        return jsonMap;
    }

    public void setJsonMap(Map<String, Object> jsonMap) {
        this.jsonMap = jsonMap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }
}
