//
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.zyguo.tools.sms.MengWangSMS;
//import com.zyguo.tools.sms.NexmoSMS;
//import com.zyguo.tools.sms.TwilioSMS;
//import com.zyguo.tools.sms.YiMeiSMS;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:applicationContext-tools-test.xml" })
//public class TestSms extends BaseTest {
//	
//	@Autowired
//	@Qualifier("yiMei")
//	private YiMeiSMS yiMei;
//	
//	@Autowired
//	@Qualifier("mengWang")
//	private MengWangSMS mengWang;
//
//	@Autowired
//	@Qualifier("twilio")
//	private TwilioSMS twilio;
//
//	@Autowired
//	@Qualifier("nexmo")
//	private NexmoSMS nexmo;
//	
//	@Test
//	public void testSms() {
//		System.out.println("test sms ");
//		String areaCode = "86";
//		String phoneNum = "13522772189";
//		String content = "test sms " + System.currentTimeMillis();
//		int maxWaitSec = 10;
//		//String result = yiMei.sendSMSBySync(areaCode, phoneNum, content + ",yimei", maxWaitSec);
//		//String result = mengWang.sendSMSBySync(areaCode, phoneNum, content + ",mengwang", maxWaitSec);
//		//String result = nexmo.sendSMSBySync(areaCode, phoneNum, content  +",nexmo", maxWaitSec);
//		String result = twilio.sendSMSBySync(areaCode, phoneNum, content + ",twilio", maxWaitSec);
//		Assert.assertTrue( true );
//	}
//	
//}
