package com.zhiyin.sms.sender.kalos;

import java.io.StringWriter;
import java.util.Date;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class VelocityUtil {

	public static String convStr(String templateStr, Map<String, String> map) {

		VelocityContext context = new VelocityContext();
		// 把数据填入上下文
		for (Map.Entry<String, String> entry : map.entrySet()) {
			context.put(entry.getKey(), entry.getValue());
		}
		
		return convStr(templateStr,context);
	}

	/**
	 * 
	 * @param templateStr
	 *            取得velocity的模版内容, 模板内容来自字符传
	 * @param context
	 *            取得velocity的上下文context
	 * @return
	 */
	public static String convStr(String templateStr, VelocityContext context) {

		// 初始化并取得Velocity引擎
		VelocityEngine ve = new VelocityEngine();
		ve.init();

		// 输出流
		StringWriter writer = new StringWriter();
		// 转换输出
		ve.evaluate(context, writer, "", templateStr); // 关键方法
		// System.out.println(writer.toString());

		return writer.toString();
	}

	// 采用字符串作为模板内容的 Velocity简单实例。
	public static void main(String[] args) throws Exception {
		// 初始化并取得Velocity引擎
		VelocityEngine ve = new VelocityEngine();
		ve.init();
		// 取得velocity的模版内容, 模板内容来自字符传
		String content = "";
		content += "Welcome  $name  to Javayou.com! ";
		content += " today is  $date.";
		// 取得velocity的上下文context
		VelocityContext context = new VelocityContext();
		// 把数据填入上下文
		context.put("name", "javaboy2012");
		context.put("date", (new Date()).toString());
		// 输出流
		StringWriter writer = new StringWriter();
		// 转换输出
		ve.evaluate(context, writer, "", content); // 关键方法
		System.out.println(writer.toString());
	}

}
