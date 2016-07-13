package com.vcg.micro.user.service.impl;

import com.vcg.micro.user.dao.UserDao;
import com.vcg.micro.user.model.User;
import com.vcg.micro.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created on 2016/7/05 11:44.
 */
@RestController
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public User selectByPrimaryKey(@PathVariable("id") Integer id) {
        return userDao.selectByPrimaryKey(id);
    }

    public List<User> list() {
        return userDao.list();
    }

    public int insert(@RequestBody User user) {
        return userDao.insert(user);
    }

    public int deleteByPrimaryKey(@PathVariable("id") Integer id) {
        return userDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKey(@RequestBody User user) {
        return userDao.updateByPrimaryKey(user);
    }
}