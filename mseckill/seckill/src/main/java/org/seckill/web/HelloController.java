package org.seckill.web;

import org.seckill.entity.Seckill;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by wangqinghui on 2016/6/16.
 */

@RestController
@RequestMapping("/")
public class HelloController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello( ) {
        System.out.println("hello");
        return "list";
    }

}
