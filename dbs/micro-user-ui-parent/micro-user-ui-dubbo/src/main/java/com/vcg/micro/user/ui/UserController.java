package com.vcg.micro.user.ui;

import com.vcg.micro.user.model.User;
import com.vcg.micro.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wuyu on 2016/7/6.
 */
@RestController
@RequestMapping(value = "/dubbo")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public User selectByPrimaryKey(@PathVariable("id") Integer id){
        return userService.selectByPrimaryKey(id);
    }
}
