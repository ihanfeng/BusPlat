package com.zhiyin.jagent.runner;

public class Controller   {

    public void setName(String name) {  
        this.name = name;  
    }  
  
    public String getName() {  
        return this.name;  
    }  
      
    private String name;  
  
    public String status() {  
        return "this is a Controller MBean,name is " + this.name;  
    }  
  
    public void start() {  
        // TODO Auto-generated method stub  
    }  
  
  
    public void stop() {  
        // TODO Auto-generated method stub  
    }  
}  