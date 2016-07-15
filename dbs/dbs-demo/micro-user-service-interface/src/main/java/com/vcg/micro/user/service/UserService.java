package com.vcg.micro.user.service;

import com.vcg.micro.user.model.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created on 2016/7/05 11:44.
 */
@FeignClient("micro-user")
public interface UserService {

    @ApiOperation(value = "根据主键id获取对象!", notes = "这个方法的主要作用是通过id获取对象,例子; http://localhost:8080/user/1", httpMethod = "GET", response = User.class)
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    User selectByPrimaryKey(
            @ApiParam(name = "id", value = "用户id", required = true, example = "http://localhost/user/1")
            @PathVariable("id") Integer id);

    @ApiOperation(value = "获取所有User对象", httpMethod = "GET", response = List.class)
    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    List<User> list();

    @ApiOperation(value = "根据主键删除对象", httpMethod = "DELETE", response = Integer.class)
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    int deleteByPrimaryKey(@PathVariable("id") Integer id);

    @ApiOperation(value = "插入对象", httpMethod = "POST", response = Integer.class, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    int insert(@RequestBody User user);

    @RequestMapping(value = "/user", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "根据主键删除对象", httpMethod = "PUT", response = Integer.class)
    int updateByPrimaryKey(@RequestBody User user);


}