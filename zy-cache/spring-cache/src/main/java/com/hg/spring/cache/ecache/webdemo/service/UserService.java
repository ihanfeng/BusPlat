package com.hg.spring.cache.ecache.webdemo.service;


import com.hg.spring.cache.ecache.webdemo.dao.UserDao;
import com.hg.spring.cache.ecache.webdemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * 业务操作，
 */
@Service("userService")
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	// 查询所有，不要key,默认以方法名+参数值+内容 作为key
	@Cacheable(value = "userCache")
	public List<User> getAll(){
		printInfo("getAll");
		return userDao.users;
	}
	// 根据ID查询，ID 我们默认是唯一的
	@Cacheable(value = "userCache", key="#id")
	public User findById(Integer id){
		printInfo(id);
		return userDao.findById(id);
	}
	// 通过ID删除
	@CacheEvict(value = "userCache", key="#id")
	public void removeById(Integer id){
		userDao.removeById(id);
	}
	
	public void addUser(User u){
		if(u != null && u.getId() != null){
			userDao.addUser(u);
		}
	}
	// key 支持条件，包括 属性condition ，可以 id < 10 等等类似操作
	// 更多介绍，请看参考的spring 地址
	@CacheEvict(value="userCache", key="#u.id")
	public void updateUser(User u){
		removeById(u.getId());
		userDao.updateUser(u);
	}

	public void removeByIdNoCache(Integer id){
		userDao.removeById(id);
	}
	
	// 修改，缓存无关
	public void updateUserNoCache(User u){
		removeByIdNoCache(u.getId());
		userDao.updateUser(u);
	}
	
	// allEntries 表示调用之后，清空缓存，默认false,
	// 还有个beforeInvocation 属性，表示先清空缓存，再进行查询
	@CacheEvict(value="userCache",allEntries=true)
	public void removeAll(){
		System.out.println("清除所有缓存");
	}
	
	private void printInfo(Object str){
		System.out.println("非缓存查询----------findById"+str);
	}
	
	
}
