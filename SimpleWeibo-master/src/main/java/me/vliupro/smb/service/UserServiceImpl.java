package me.vliupro.smb.service;

import me.vliupro.smb.dao.UserDao;
import me.vliupro.smb.dao.UserDaoImpl;
import me.vliupro.smb.po.User;

/**
 * Created by vliupro on 16-5-31.
 */
public class UserServiceImpl implements UserService {

    UserDao ud = new UserDaoImpl();

    @Override
    public boolean addUser(User user) {
        return ud.addUser(user);
    }

    @Override
    public boolean checkEmail(String email) {
        return ud.isExistEmail(email);
    }

    @Override
    public boolean checkNickName(String nickname) {
        return ud.isExistNickname(nickname);
    }

    @Override
    public String getPasswdByEmail(String email) {
        return ud.getPasswdByEmail(email);
    }

    @Override
    public User getUserByEmail(String email) {
        return ud.getUserByEmail(email);
    }

    @Override
    public User getUserById(int userId) {
        return ud.getUserById(userId);
    }

    @Override
    public User getUserByUserName(String username) {
        return ud.getUserByNickName(username);
    }
}
