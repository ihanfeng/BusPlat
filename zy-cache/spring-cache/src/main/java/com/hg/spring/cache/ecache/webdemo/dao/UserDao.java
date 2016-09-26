package com.hg.spring.cache.ecache.webdemo.dao;


import com.hg.spring.cache.ecache.webdemo.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 静态数据，模拟数据库操作
 */
@Repository("userDao")

public class UserDao {
	
	public List<User> users = initUsers();
	
	public User findById(Integer id){
		User user = null;
		for(User u : users){
			if(u.getId().equals(id)){
				user = u;
			}
		}
		return user;
	}
	
	public void removeById(Integer id){
		User user = null;
		for(User u : users){
			if(u.getId().equals(id)){
				user = u;
				break;
			}
		}
		users.remove(user);
	}
	
	public void addUser(User u){
		users.add(u);
	}
	
	public void updateUser(User u){
		addUser(u);
	}
	
	
	// 模拟数据库
	private List<User> initUsers(){
		List<User> users = new ArrayList<User>();
		User u1 = new User(1,"张三","123");
		User u2 = new User(2,"李四","124");
		User u3 = new User(3,"王五","125");
		users.add(u1);
		users.add(u2);
		users.add(u3);
		return users;
	}
}
