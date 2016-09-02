/*
 * This is sample 
 * 
 */
package com.netprot.trinity.test;

import org.junit.Assert;
import org.junit.Test;

import com.netpro.trinity.sample.HelloWorld;

/**
 * Hello World unit test
 * 
 * @author troychen
 *
 */
public class HelloWorldTest {

	@Test
	public void testSayHello() {
		
		HelloWorld h = new HelloWorld("troy");
		Assert.assertEquals("Hello World troy", h.sayHello());
	}
	
}
