import com.hg.msg.service.IMsgNotifyService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestLoginService {

    static ClassPathXmlApplicationContext applicationContext;

    @BeforeClass
    public static void initSpring() {
        applicationContext = new ClassPathXmlApplicationContext(
                "classpath:context/applicationContext-dubbo.xml");
        applicationContext.start();
    }

    @AfterClass
    public static void destroySpring() {
        ((AbstractApplicationContext) applicationContext).destroy();
    }

    @Test
    public void registServices() throws InterruptedException {
        System.out.println("applicationContextStartupDate:" + applicationContext.getStartupDate());

        IMsgNotifyService msgNotifyService = applicationContext.getBean("msgNotifyService", IMsgNotifyService.class);

        String ok = msgNotifyService.testok("admin");

        System.out.println("msgNotifyService return:" + ok);

    }

}