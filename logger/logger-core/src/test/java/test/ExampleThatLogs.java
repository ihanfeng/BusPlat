package test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple class that we use to trigger a log statement.
 */
public class ExampleThatLogs {

    private static final Logger LOG = LoggerFactory.getLogger(ExampleThatLogs.class);

    public String concat(String a, String b) {
        LOG.info("String a:" + a + ", String b:" + b);
        return a+b;
    }
}
