import org.junit.Test;
import org.slf4j.LoggerFactory;


/**
 * Created by colinsu on 2016/4/14.
 */
public class LogstashTest {
    private static final org.slf4j.Logger LGR = LoggerFactory.getLogger(LogstashTest.class);

    @Test
    public void test() {
        while(true) {
            LogstashTest obj = new LogstashTest();
            try {
                obj.divide();
            } catch (ArithmeticException ex) {
                LGR.error("ddddddddddddddddddddddddddddddddd大家好111!", ex);
            }
        }
    }
    private void divide(){
        int i = 10 /0;
    }
}