package com.aoploging;

import com.github.nickvl.xspring.core.log.aop.annotation.LogInfo;
import org.springframework.stereotype.Service;

import java.util.Date;

@LogInfo
@Service
public class DemoService {


    @LogInfo
    public User processPaymentContract(String request) {

        User u = new User();
        u.setName("ss");
        u.setCreateTime(new Date());
        return u;
    }

    public User demo2(String request) {

        User u = new User();
        u.setName("ss");
        u.setCreateTime(new Date());
        return u;
    }


    public User ignore(String request) {

        User u = new User();
        u.setName("ss");
        u.setCreateTime(new Date());
        return u;
    }


}

class User {
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