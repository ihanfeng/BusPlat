package com.anylog.test;


public class TestException {
	
	static int a = 0;
	
	public static void main(String[] args) throws InterruptedException {
		while (true) {		
			runBody();
		}
	}

	public  static void runBody() throws InterruptedException{
		
		try {
			
			a++;
			
			System.out.println(a);

//			System.out.println(a / 0);

		} catch (Exception e) {
			//System.out.println("java.lang.ArithmeticException");
		}
		Thread.sleep(5000);
	}

}
