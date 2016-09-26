package com.hg.spring.cache.ecache.webdemo.controller;

import com.hg.spring.cache.ecache.webdemo.entity.User;
import com.hg.spring.cache.ecache.webdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UserControl {
	
	@Autowired
	private UserService userService;
	
	// 提前绑定视图，提前绑定数据，方便查看数据变化
	@ModelAttribute("users")
	public List<User> cityList() {
	    return userService.getAll();
	} 

	// 根据ID查询
	@RequestMapping("/get/{id}")
	public String getUserById(Model model,@PathVariable Integer id){
		User u = userService.findById(id);
		System.out.println("查询结果："+u);
		model.addAttribute("user", u);
		return "forward:/jsp/edit";
	}
	// 删除
	@RequestMapping("/del/{id}")
	public String deleteById(Model model,@PathVariable Integer id){
		printInfo("删除-----------");
		userService.removeById(id);
		return "redirect:/jsp/view";
	}
	// 添加
	@RequestMapping("/add")
	public String addUser(Model model,@ModelAttribute("user") User user){
		printInfo("添加----------");
		userService.addUser(user);
		return "redirect:/jsp/view";
	}
	// 修改
	@RequestMapping("/update")
	public String update(Model model,@ModelAttribute User u){
		printInfo("开始更新---------");
		userService.updateUser(u);
		model.addAttribute("user", u);
		return "redirect:/jsp/view";
	}
	// 清空所有
	@RequestMapping("/remove-all")
	public String removeAll(){
		printInfo("清空-------------");
		userService.removeAll();
		return "forward:/jsp/view";
	}
	// JSP 跳转
	@RequestMapping("/jsp/{jspName}")
	public String toJsp(@PathVariable String jspName){
		System.out.println("JSP TO -->>" +jspName);
		return jspName;
	}
	
	private void printInfo(String str){
		System.out.println(str);
	}
}
