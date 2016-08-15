package com.hg.ratelimiter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

//	 static Logger logger = LoggerFactory.getLogger(HelloController.class);
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		System.out.println("hello");
		model.addAttribute("message", "Spring 3 MVC Hello World");
		return "hello";

	}
	
	@RequestMapping(value = "/ratelimiter/testfilter", method = RequestMethod.GET)
	public String testFilter(ModelMap model) {

		System.out.println("hello");
		model.addAttribute("message", "Spring 3 MVC Hello World");
		return "hello";

	}
	
	

	@RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
	public ModelAndView hello(@PathVariable("name") String name) {

		
		ModelAndView model = new ModelAndView();
		model.setViewName("hello");
		model.addObject("msg", name);

		return model;

	}

}