package me.vliupro.smb.service;

import me.vliupro.smb.po.User;

/**
 * Created by vliupro on 16-5-31.
 */
public interface UserService {

    /**
     * 注册-添加用户
     * @param user
     * @return
     */
    boolean addUser(User user);

    /**
     * 判断email是否被使用
     * @param email
     * @return
     */
    boolean checkEmail(String email);

    /**
     * 判断nickname是否被使用
     * @param nickname
     * @return
     */
    boolean checkNickName(String nickname);

    /**
     * 获得密码
     * @param email
     * @return
     */
    String getPasswdByEmail(String email);

    /**
     * 根据email获得用户
     * @param email
     * @return
     */
    User getUserByEmail(String email);

    /**
     * 根据ID获得用户
     * @param userId
     * @return
     */
    User getUserById(int userId);

    User getUserByUserName(String username);
}
