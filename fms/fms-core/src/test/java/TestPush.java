//
//
//import java.util.HashMap;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.zyguo.tools.push.HuaweiPush;
//import com.zyguo.tools.push.PushBody;
//import com.zyguo.tools.push.UmengPush;
//import com.zyguo.tools.push.XiaoMiPush;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:applicationContext-tools-test.xml" })
//public class TestPush extends BaseTest {
//	
//	@Autowired
//	@Qualifier("xiaoMiPush")
//	private XiaoMiPush xiaoMiPush;
//
//	@Autowired
//	@Qualifier("huaweiPush")
//	private HuaweiPush huaweiPush;
//
//	@Autowired
//	@Qualifier("umengPush")
//	private UmengPush umengPush;
//	
//	private PushBody createPushBody( String token, int noticeType ){
//		PushBody body = new PushBody();
//		long t = System.currentTimeMillis();
//		body.setNoticeDesc( noticeType + ",test notice," + t );
//		body.setNoticeTitle( noticeType + ",test title," + t );
//		body.setTicker("test ticker," + t);
//		HashMap<String, String> params = new HashMap<String, String>();
//		params.put("fid", 10+"");
//		params.put("tid", 300+"");
//		body.setParams( params );
//		body.setOpenTone( true );
//		body.setOpenVibration( true );
//		body.setToken( token );
//		body.setToneUrl("default");
//		body.setNoticeType( noticeType );
//		return body;
//	}
//
//	@Test
//	public void testUmengPush() {
//		System.out.println("test umeng Push  ");
//		int maxWaitSec = 10;
//		String token = "AvGtJ06FlKtlkkQETTjQOu2LKFZoUIH6R4RL0MSeH08B";
//		PushBody pb = createPushBody( token, 1  );
//		String result = umengPush.syncSendPush( pb, maxWaitSec );
//		//String result = umengPush.syncSendPushToApp( pb, maxWaitSec );
//		Assert.assertTrue( true );
//	}
//	
//	//@Test
//	public void testXiaomiPush() {
//		System.out.println("test Xiaomi Push  ");
//		int maxWaitSec = 10;
//		String regId="d//igwEhgBGCI2TG6lWqlA6DnFrKm8AEX/eux/Z4002ucHu2JVx9sqFNGPWDN+DHqV3cQmMCmozkwZZINKJhRNuie2ULbXOMvp0rztn63oU=";
//		String alais="1CFE30739FF69478971C1D7ECA4EF088";
//		String token = "86FDFCC4ADAADE25C5EECE9F3D7F1FCB";
//		PushBody pb1 = createPushBody( alais, 1 );
//		PushBody pb2 = createPushBody( alais, 2 );
//		PushBody pb3 = createPushBody( alais, 8 );
//		String result1 = xiaoMiPush.syncSendPush( pb1, maxWaitSec);
//		//String result2 = xiaoMiPush.syncSendPush( pb2, maxWaitSec);
//		//String result3 = xiaoMiPush.syncSendPush( pb3, maxWaitSec);
//		
//		//String result = xiaoMiPush.syncSendPushToApp( pb, maxWaitSec);
//		Assert.assertTrue( true );
//	}
//
//	
//	//@Test
//	public void testHuaweiPush() {
//		System.out.println("test huawei Push  ");
//		int maxWaitSec = 10;
//		String token = "08625550247022680000003110000001";
//		PushBody pb = createPushBody( token, 1  );
//		//String result = huaweiPush.syncSendPush( pb, maxWaitSec );
//		String result = huaweiPush.syncSendPushToApp( pb, maxWaitSec );
//		Assert.assertTrue( true );
//	}
//	
//}
