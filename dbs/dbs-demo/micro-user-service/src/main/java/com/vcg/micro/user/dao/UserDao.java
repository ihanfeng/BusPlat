package com.vcg.micro.user.dao;

import com.vcg.micro.user.model.User;

import java.util.List;

/**
 * Created on 2016/7/05 11:44.
 */
public interface UserDao {

    public User selectByPrimaryKey(Integer id);

    public List<User> list();

    public int deleteByPrimaryKey(Integer id);

    public int insert(User user);

    public int updateByPrimaryKey(User user);
}