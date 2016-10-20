package com.zhiyin.logconf.demo;

import com.zhiyin.logconf.annotation.Logger;
import com.zhiyin.logconf.annotation.LoggerIgnore;
import org.springframework.stereotype.Component;

import java.util.Date;

@Logger
@Component
public class UserService {

    @Logger
    public String ask(String name) {

        return "How are you, " + name + "?";
    }

//    @Logger
    public User bye() {

            User u = new User();
            u.setName("ss");
            u.setCreateTime(new Date());
            return u;
        }

    @LoggerIgnore
    public User ignore( ) {
        User u = new User();
        u.setName("ss");
        u.setCreateTime(new Date());
        return u;
    }

    }



    class User{
        private String name;
        private Date createTime;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }
    }