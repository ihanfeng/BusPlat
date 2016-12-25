import ch.qos.logback.core.joran.spi.JoranException;
import com.zhiyin.logger.logback.LogbackConfigLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by wangqinghui on 2016/12/22.
 */
public class SentryTest {

    public static void main(String[] args) {
        LogbackConfigLoader.load("logback-sentry.xml");
        Logger logger = LoggerFactory.getLogger("snail");

        logger.error("Hello");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
