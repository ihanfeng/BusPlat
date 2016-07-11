package me.vliupro.smb.po;

import me.vliupro.smb.utils.Md5;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vliupro on 16-5-22.
 */
public class User {

    private int userId; // 主键ID
    private String nickName; //昵称
    private String password; //密码
    private String email; //邮箱

    public User(){

    }

    public User(String nickName, String password, String email) {
        this.nickName = nickName;
        this.password = User.encryption(password);
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    public static String encryption(String password) {
        return Md5.encryption(password);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public Map<String, Object> toMap() {
        Map<String, Object> userMap = new HashMap<String, Object>();
        userMap.put("id", this.getUserId());
        userMap.put("nickname", this.getNickName());
        userMap.put("email", this.getEmail());
        return userMap;
    }

    public void mapToUser(Map<String, Object> map) {
        this.setUserId(Integer.parseInt(map.get("id").toString()));
        this.setNickName(map.get("nickname").toString());
        this.setEmail(map.get("email").toString());
    }
}
