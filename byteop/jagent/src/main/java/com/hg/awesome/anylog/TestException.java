package com.hg.awesome.anylog;


public class TestException {
	
	static int a = 0;
	
	public static void main(String[] args) throws InterruptedException {
		while (true) {		
			runbody();
		}
	}

	public  static void runbody() throws InterruptedException{
		
		int b=0;
		int c = 1;
		
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
