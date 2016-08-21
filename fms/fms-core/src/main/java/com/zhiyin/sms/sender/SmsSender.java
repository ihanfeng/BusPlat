package com.zhiyin.sms.sender;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhiyin.sms.sender.kalos.KarlosSMS;

@Slf4j
public class SmsSender {

    public static boolean send(String tel,String msg){
        msg += "【语程】";
        log.info("send msg to {}, content is {}",tel,msg);
        KarlosSMS.sendSMS(tel, msg);
        // TODO add sms send succ check.
        return true;
    }

    @Deprecated
	public static void sendUserRegSms(String telephone,String code){
		String content = "您的验证码是："+code+"【知音】";
		log.info("send msg to {}, content is {}",telephone,code);
		KarlosSMS.sendSMS(telephone, content);
	}

}
