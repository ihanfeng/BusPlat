package me.vliupro.smb.dao;

import me.vliupro.smb.po.User;

import java.util.Map;

/**
 * Created by vliupro on 16-5-23.
 */
public class UserDaoImpl extends BaseImpl implements UserDao {

    public boolean addUser(User user) {
        String sql = "insert db_user (nickname, password, email) values(?,?,?)";
        int count = this.db.update(sql, user.getNickName(), user.getPassword(), user.getEmail());
        return count > 0;
    }

    public boolean isExistNickname(String nickname) {
        String sql = "select nickname from db_user where nickname=?";
        Map<String, Object> map = this.db.query(sql, nickname);
        if (map == null) {
            return false;
        } else {
            return map.containsKey("nickname");
        }
    }

    public boolean isExistEmail(String email) {
        String sql = "select email from db_user where email=?";
        Map<String, Object> map = this.db.query(sql, email);
        if (map == null) {
            return false;
        } else {
            return map.containsKey("email");
        }
    }

    public String getPasswdByEmail(String email) {
        String sql = "select password from db_user where email=?";
        Map<String, Object> map = this.db.query(sql, email);
        if (map != null) {
            return map.get("password").toString();
        } else {
            return null;
        }
    }

    public User getUserByEmail(String email) {
        String sql = "select * from db_user where email=?";
        Map<String, Object> userMap = this.db.query(sql, email);
        if (userMap != null) {
            return (User) this.generate(userMap);
        } else {
            return null;
        }
    }

    public User getUserById(int userId) {
        String sql = "select * from db_user where id=?";
        Map<String, Object> userMap = this.db.query(sql, userId);
        if (userMap != null) {
            return (User) this.generate(userMap);
        } else {
            return null;
        }
    }

    @Override
    public User getUserByNickName(String nickname) {
        String sql = "select * from db_user where nickname=?";
        Map<String, Object> userMap = this.db.query(sql, nickname);
        if (userMap != null) {
            return (User) this.generate(userMap);
        } else {
            return null;
        }
    }

    @Override
    protected Object generate(Map<String, Object> map) {
        User user = new User();
        user.setUserId(Integer.parseInt(map.get("id").toString()));
        user.setNickName(map.get("nickname").toString());
        user.setPassword(map.get("password").toString());
        user.setEmail(map.get("email").toString());
        return user;
    }

}
