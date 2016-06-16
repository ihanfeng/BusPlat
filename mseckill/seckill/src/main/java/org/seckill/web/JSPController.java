package org.seckill.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class JSPController {

  @RequestMapping("/jsptest")
    public ModelAndView test( ) {
      log.info("test jps page req.");

    ModelAndView modelAndView = new ModelAndView(  "jsp-spring-boot" );
    modelAndView.addObject("notes", "");
    return modelAndView;

  }
    
}