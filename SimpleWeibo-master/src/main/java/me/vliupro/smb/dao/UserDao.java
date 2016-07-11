package me.vliupro.smb.dao;

import me.vliupro.smb.po.User;

/**
 * Created by vliupro on 16-5-23.
 */
public interface UserDao {

    /**
     * 增加用户
     * @param user
     * @return
     */
    boolean addUser(User user);

    /**
     * 判断nickname是否被使用
     * @param nickname
     * @return
     */
    boolean isExistNickname(String nickname);

    /**
     * 判断email是否被使用
     * @param email
     * @return
     */
    boolean isExistEmail(String email);

    /**
     * 由email获得password
     * @param email
     * @return
     */
    String getPasswdByEmail(String email);

    /**
     * 根据Email获取用户
     * @param email
     * @return
     */
    User getUserByEmail(String email);

    /**
     * 根据Id获得用户
     * @param userId
     * @return
     */
    User getUserById(int userId);

    /**
     * 根据nickname获取用户
     * @param nickname
     * @return
     */
    User getUserByNickName(String nickname);

}
