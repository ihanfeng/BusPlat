package test;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.Appender;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

/**
 * Our Test class shows how we can hook into logback with mockito to see the
 * logging activity
 */
@RunWith(MockitoJUnitRunner.class)
public class ExampleThatLogsTest {

    @Mock
    private Appender mockAppender;
    //Captor is genericised with ch.qos.logback.classic.spi.LoggingEvent
    @Captor
    private ArgumentCaptor<LoggingEvent> captorLoggingEvent;

    //I've cheated a little here and added the mockAppender to the root logger
    //It's not quite necessary but it also shows you how it can be done
    @Before
    public void setup() {
        final Logger logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        logger.addAppender(mockAppender);
    }

    //Always have this teardown otherwise we can stuff up our expectations. Besides, it's
    //good coding practise
    @After
    public void teardown() {
        final Logger logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        logger.detachAppender(mockAppender);
    }

    @Test
    public void shouldConcatAndLog() {
        //given
        ExampleThatLogs example = new ExampleThatLogs();
        //when
        final String result = example.concat("foo", "bar");
        //then
        assertEquals("foobar", result);

        //Now verify our logging interactions
        verify(mockAppender).doAppend(captorLoggingEvent.capture());
        //Having a genricised captor means we don't need to cast
        final LoggingEvent loggingEvent = captorLoggingEvent.getValue();
        //Check log level is correct
        assertThat(loggingEvent.getLevel(), is(Level.INFO));
        //Check the message being logged is correct
        assertThat(loggingEvent.getFormattedMessage(),
                is("String a:foo, String b:bar"));
    }
}