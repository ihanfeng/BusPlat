package com.zhiyin.j2cache;

import java.io.Serializable;

public class Account implements Serializable {
   private int id; 
   private String name;

    public Account( ) {

    }

    public Account(String name) {
     this.name = name; 
   } 
   public int getId() { 
     return id; 
   } 
   public void setId(int id) { 
     this.id = id; 
   } 
   public String getName() { 
     return name; 
   } 
   public void setName(String name) { 
     this.name = name; 
   } 
 }