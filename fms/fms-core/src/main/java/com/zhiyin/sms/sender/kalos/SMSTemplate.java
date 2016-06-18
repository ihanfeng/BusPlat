package com.zhiyin.sms.sender.kalos;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class SMSTemplate {

	// public static SMSTemplate temp = new SMSTemplate();
	// public static Map<String,String> smsTemplate = new
	// HashMap<String,String>();
	public static String smsTempCh = "property/sms/templates/sms_zh_CN.properties";

	/**
	 * 转化成规定格式
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static void convToStandSMSTemplate() throws Exception {
		
		
		String templateFile = smsTempCh;
		CompositeConfiguration config = new CompositeConfiguration();
		try {
			config.addConfiguration(new PropertiesConfiguration(templateFile));
		} catch (ConfigurationException e) {
			throw new SmsException("未找到模版文件:" + templateFile + "。");
		}

		Iterator<String> keys = config.getKeys();

		while (keys.hasNext()) {
			String key = keys.next();
			String newStr = config.getString(key).replaceAll("\\$\\{[^}]*\\}", "@");
			System.out.println(newStr);
		}
	}
	
	
	/**
	 * 根据模版名称获取模版内容
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String getSMSTemplate(String str) throws SmsException {
		String templateFile = smsTempCh;
		CompositeConfiguration config = new CompositeConfiguration();
		try {
			config.addConfiguration(new PropertiesConfiguration(templateFile));
		} catch (ConfigurationException e) {
			throw new SmsException("未找到模版文件:" + templateFile + "。");
		}

		Iterator<String> keys = config.getKeys();

		while (keys.hasNext()) {
			String key = keys.next();
			if (key.equals(str)) {
				return config.getString(key);
			}
		}
		throw new SmsException("未找到模板字符串：" + str + "。");
	}

	
	public static void main(String[] args) throws Exception {
		
//		卡洛斯模版
		convToStandSMSTemplate();
		System.out.println();
		
		String templateStr = SMSTemplate
				.getSMSTemplate("pc_order_audit_reject");
		Map<String,String> varValMap = new HashMap<String,String>();
		String username="test";
		varValMap.put("username",username);
		varValMap.put("ordertime", DateUtil.dateFormat(DateUtil.currentDate(),DateUtil.dateformatstr1));
		String str = VelocityUtil.convStr(templateStr, varValMap);
		System.out.println(str);		
	
	}
}
