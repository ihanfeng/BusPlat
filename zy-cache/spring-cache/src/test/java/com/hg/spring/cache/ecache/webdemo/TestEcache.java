package com.hg.spring.cache.ecache.webdemo;

import com.alibaba.fastjson.JSON;
import com.hg.spring.cache.ecache.webdemo.entity.User;
import com.hg.spring.cache.ecache.webdemo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-ecache.xml")
public class TestEcache {
	
	@Autowired
	UserService userService;
	
	
	@Test
	public void encrypt() {
		User tmp = userService.findById(1);
		System.out.println("非缓存读取 "+JSON.toJSONString(tmp));
		
		tmp = userService.findById(1);
		System.out.println("从缓存中读取 " +JSON.toJSONString(tmp));
		
		tmp.setName("SB");
		userService.updateUserNoCache(tmp);
		
		tmp = userService.findById(2);
		System.out.println("修改后的效果 "+JSON.toJSONString(tmp));
		
		tmp = userService.findById(1);
		System.out.println("修改后的效果 "+JSON.toJSONString(tmp));
	}

}
