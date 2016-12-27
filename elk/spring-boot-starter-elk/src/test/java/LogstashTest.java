import com.zhiyin.elk.boot.autoconfig.LogAppenderUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.MDC;
import org.junit.Test;
import org.slf4j.LoggerFactory;


/**
 * Created by colinsu on 2016/4/14.
 */
@Slf4j
public class LogstashTest {
    private static final org.slf4j.Logger LGR = LoggerFactory.getLogger(LogstashTest.class);

    @Test
    public void genFileAppender() {
        MDC.put("traceId","trace-id-100");
        LogAppenderUtil.addFileAppender("gen-file");
        log.info("gen file.");
    }

    @Test
    public void test() {
        while (true) {

            LGR.info("sss");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}