import com.zhiyin.logger.logback.LogbackConfigLoader;
import com.zhiyin.logger.logback.LoggerAppenderUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wangqinghui on 2016/12/22.
 */
@Slf4j
public class AppenderTest {


    @Test

public void appenderTest(){
        LogbackConfigLoader.load("logback-addappender.xml");

        LoggerAppenderUtil.addAppender("root");
//        LoggerAppenderUtil.addAppender("root");

        for (int i = 0; i < 10 ; i++) {
            log.info("testappender");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
