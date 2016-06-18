package com.zhiyin.sms.sender.kalos;

import java.util.HashMap;
import java.util.Map;

public class KarlosSMS {
	
	
	public static void sendSMS(String telephone,String templateName,Map<String, String> varValMap) throws SmsException {
		String templateStr="";

		templateStr = SMSTemplate.getSMSTemplate(templateName);
		
		String sendMsgStr = VelocityUtil.convStr(templateStr, varValMap);
//		System.out.println("短信内容："+sendMsgStr);
		
		KarlosSmsTest.keyword(sendMsgStr);
//		sendMsgStr ="王同学您好，您的机房预约申请已审核通过，预约号为S1024，使用时段为2015-02-21 19:15:32-2015-02-21 19:15:32；请提前到机房管理办公室授权门卡，妥善安排好时间，遵守机房管理规定。【电影学院摄影系】";

		KarlosSmsTest.sendMsg(telephone, sendMsgStr);
		
	}
	
	public static void sendSMS(String telephone,String content) throws SmsException {


		
//		KarlosSmsTest.keyword(content);
//		sendMsgStr ="王同学您好，您的机房预约申请已审核通过，预约号为S1024，使用时段为2015-02-21 19:15:32-2015-02-21 19:15:32；请提前到机房管理办公室授权门卡，妥善安排好时间，遵守机房管理规定。【电影学院摄影系】";

		KarlosSmsTest.sendMsg(telephone, content);
		
	}
	
	
	public static void main(String[] args){
		
		Map<String,String> varValMap = new HashMap<String,String>();
		String username="王同学";
		varValMap.put("firstName",username);
		varValMap.put("lastName", "S1024");
//		varValMap.put("starttime", DateUtil.dateFormat(DateUtil.currentDate(),DateUtil.dateformatstr1));
//		varValMap.put("endtime", DateUtil.dateFormat(DateUtil.currentDate(),DateUtil.dateformatstr1));
//		
		try {
			sendSMS("18811591895","register_succ",varValMap);
		} catch (SmsException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
