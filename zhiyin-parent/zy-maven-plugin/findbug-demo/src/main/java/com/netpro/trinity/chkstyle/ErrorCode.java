package com.netpro.trinity.chkstyle;

public class ErrorCode {

	
	public static void main(String [] args) {
		int b[] = null;
		
		int result = 0;
		for(int i = 0; i < 4; i++)
		  result = ((result << 8) + b[i]);
	}
	
}
