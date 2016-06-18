package com.zhiyin.sms.sender.kalos;

import java.io.IOException;
import java.io.StringReader;
import java.net.URLEncoder;

import org.apache.commons.digester3.Digester;
import org.xml.sax.SAXException;

import com.ruanwei.interfacej.SmsClientKeyword;
import com.ruanwei.interfacej.SmsClientOverage;
import com.ruanwei.tool.SmsClientAccessTool;


public class KarlosSmsTest {

	public static String url = "http://121.199.50.122:8888/sms.aspx";
	public static String userid = "1114";
	public static String account = "youliao";
	public static String password = "youliao753951";
//	public static String checkWord = "面试是关键字";
	public static String checkWord = "呵呵";

	public static void main(String[] args) throws Exception {

//		keyword(checkWord);
//		 overage();
//		 test();
		sendMsg("18811591895","订单审核通过【北京电影学院摄影系】");
	}

	public static void keyword( ) {

		String keyword = SmsClientKeyword.queryKeyWord(url, userid, account,
				password, checkWord);
		
		
		System.out.println(keyword);
	}
	
	public static void keyword(String str) {
		try{
		String keyword = SmsClientKeyword.queryKeyWord(url, userid, account,
				password, str);
		
		if(SmsReturnInfoParser.keywordParser(keyword)==false){
			throw new SmsException("短信内容包含关键字：");
		}
		
		}catch (SmsException e) {
			throw new SmsException(e.getMessage());
		}catch (Exception e) {
			throw new SmsException("短信关键字查询的返回信息不能解析");
		}
	}

	public static SmsoverageEntity overage(){
		
		String overagexml = SmsClientOverage.queryOverage(url, userid, account,
				password);
		System.out.println(overagexml);
//		String overagexml="<?xml version=\"1.0\" encoding=\"utf-8\" ?><returnsms> <returnstatus>Faild</returnstatus> <message>用户名或密码错误</message> <remainpoint>0</remainpoint> <taskID>0</taskID> <successCounts>0</successCounts></returnsms>";
		Digester digester=new Digester();
		digester.setValidating(false);

		digester.addObjectCreate("returnsms", SmsoverageEntity.class);
		digester.addBeanPropertySetter("returnsms/returnstatus");	        
		digester.addBeanPropertySetter("returnsms/message");  
		digester.addBeanPropertySetter("returnsms/payinfo");  
		digester.addBeanPropertySetter("returnsms/overage");  
		digester.addBeanPropertySetter("returnsms/sendTotal");  
		
		
		try {
			SmsoverageEntity ret = (SmsoverageEntity) digester.parse(new StringReader(overagexml));		
//			if(ret.getReturnstatus().equals("Sucess")){
//				ret =(SmsOverage) digester.parse(new StringReader(overagexml));				
//			}
			System.out.println(ret.getReturnstatus()+ret.getMessage()+ret.getPayinfo());
			return ret;
		} catch (SAXException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			
		}catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
	
		SmsoverageEntity ret = new SmsoverageEntity();
		ret.setReturnstatus("Faild");
		ret.setMessage("余额查询接口不能解析");
		System.out.println("余额查询接口不能解析");
		return ret;
	}

	public static void test(){
		String send = SmsClientAccessTool.getInstance().doAccessHTTPPost(
				"http://121.199.50.122:8888/sms.aspx", "", "utf-8");
		System.out.println(send);
	}
	
	
	public static void sendMsg(String telStr,String msg) throws SmsException{

		try {
			
			String sendmsg = URLEncoder.encode(msg, "UTF-8");
			String url = "http://121.199.50.122:8888/sms.aspx?action=send&userid="+userid+"&account="+account+"&password="+password+"&mobile="+telStr+"&content="+sendmsg+"&sendTime=&extno=";
			String retXml = SmsClientAccessTool.getInstance().doAccessHTTPPost(url, "", "utf-8");

			
			System.out.println(retXml);
					
			Digester digester=new Digester();
			digester.setValidating(false);
			digester.addObjectCreate("returnsms", SmsoverageEntity.class);
			digester.addBeanPropertySetter("returnsms/returnstatus");	        
			digester.addBeanPropertySetter("returnsms/message");  
			
			SmsoverageEntity ret = (SmsoverageEntity) digester.parse(new StringReader(retXml));		
			if(ret.getReturnstatus().equals("Success")){
//				ret =(SmsOverage) digester.parse(new StringReader(retXml));
				return;
			}			
//			System.out.println(ret.getReturnstatus()+ret.getMessage());
//			throw new SmsException(ret.getMessage());
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new SmsException("短信返回信息不能解析");
	}
//	public static void parse
}
