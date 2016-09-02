/*
 * This is sample 
 * 
 */
package com.netpro.trinity.sample;

/**
 * Hello World Sample
 * 
 * @author troychen
 *
 */
public class HelloWorld {
	
	private String name;
	
	public HelloWorld (String name) {
		this.name = name;
	}

	public String sayHello() {
		return "Hello World " + name;
	}
}
