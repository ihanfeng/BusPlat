package com.zhiyin.sms.sender.kalos;

import java.io.IOException;
import java.io.StringReader;

import org.apache.commons.digester3.Digester;
import org.xml.sax.SAXException;

public class SmsReturnInfoParser {

	
	public static boolean keywordParser(String returnStr){
		Digester digester=new Digester();
		digester.setValidating(false);

		digester.addObjectCreate("returnsms", KeywordEntity.class);
		digester.addBeanPropertySetter("returnsms/returnstatus");	        
		digester.addBeanPropertySetter("returnsms/message");  
		digester.addBeanPropertySetter("returnsms/checkCounts"); 
		try {
			KeywordEntity ret = (KeywordEntity) digester.parse(new StringReader(returnStr));
			if(!ret.getReturnstatus().equals("Success")){
				throw new SmsException("短信内容的关键字查询返回信息不能解析"); 
			}
			
//			System.out.println(ret.getMessage());
			if(ret.getMessage().startsWith("包含屏蔽字符")){
				throw new SmsException("短信内容"+ret.getMessage()); 
			}
			
			return true;
		} catch (SAXException e) {
			e.printStackTrace();
			throw new SmsException("短信内容的关键字查询返回信息不能解析"); 
		}catch (IOException e) {
			e.printStackTrace();
			throw new SmsException("短信内容的关键字查询返回信息不能解析"); 
		}
		
//		return null;
	}
}
