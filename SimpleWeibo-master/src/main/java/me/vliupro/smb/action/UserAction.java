package me.vliupro.smb.action;

import com.opensymphony.xwork2.ActionSupport;
import me.vliupro.smb.po.User;
import me.vliupro.smb.service.UserService;
import me.vliupro.smb.service.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

/**
 * Created by vliupro on 16-5-24.
 */
public class UserAction extends ActionSupport {

    private Logger logger = LogManager.getLogger(UserAction.class);

    private UserService us;

    public UserAction() {
        us = new UserServiceImpl();
    }

    private String username;
    private String password;
    private String email;
    private String remember;

    private String securityCode;

    /**
     * 登陆
     * @return
     */
    public String login(){
        boolean isRemember = false;
        if (this.getRemember() != null) {
            isRemember = this.remember.equals("on");
        }
        if (username != null) {
            User userN = us.getUserByUserName(username);
            if (userN != null && User.encryption(password).equals(userN.getPassword())) {
                //取出user信息存入session
                User user = us.getUserByUserName(username);
                ServletActionContext.getRequest().getSession().setAttribute("user", user.toMap());
                if(isRemember) {
                    ServletActionContext.getRequest().getSession().setMaxInactiveInterval( 60 * 60 * 24 * 7 );//一个星期
                } else {
                    ServletActionContext.getRequest().getSession().setMaxInactiveInterval( 60 * 60 * 24 );
                }
                return SUCCESS;
            }
        }
        return ERROR;
    }

    /**
     * 注册
     * @return
     */
    public String register(){
        String security = (String) ServletActionContext.getRequest().getSession().getAttribute("SESSION_SECURITY_CODE");
        if (us.checkNickName(username) || us.checkEmail(email) ) {
            return ERROR;
        } else {
            if(security != null && security.equals(securityCode)) {
                User user = new User();
                user.setEmail(email);
                user.setNickName(username);
                user.setPassword(User.encryption(password));
                if (us.addUser(user)) {
                    return SUCCESS;
                } else {
                    logger.error("注册失败，email: " + email + ", username: " + username + ", password: " + password);
                    return ERROR;
                }
            } else {
                return ERROR;
            }
        }
    }

    /**
     * 登出
     * @return
     */
    public String logout(){
        ServletActionContext.getRequest().getSession().removeAttribute("user");
        return SUCCESS;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemember() {
        return remember;
    }

    public void setRemember(String remember) {
        this.remember = remember;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }
}
