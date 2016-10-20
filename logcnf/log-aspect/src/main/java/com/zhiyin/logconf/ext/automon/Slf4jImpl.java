package com.zhiyin.logconf.ext.automon;

import com.github.nickvl.xspring.core.log.aop.SimpleLogAdapter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.automon.implementations.OpenMon;
import org.automon.implementations.TimerContext;
import org.automon.utils.Utils;
import org.omg.PortableServer.AdapterActivator;

@Slf4j
public final class Slf4jImpl implements OpenMon<TimerContext> {
    private static final Object NOOP = new Object();

    @Override
    public TimerContext start(JoinPoint.StaticPart jp) {
        log.info("start: {}", Utils.getLabel(jp) );

        SimpleLogAdapter adapterActivator = new SimpleLogAdapter();
//        adapterActivator.toMessage("ss",jp.)
        return new TimerContext(jp);
    }

    @Override
    public void stop(TimerContext context) {
        log.info("stop: {}ms",context.stop() );
    }

    @Override
    public void stop(TimerContext context, Throwable throwable) {
        log.info("stop(..) ms.: "+context.stop()+" - Exception: "+throwable);
    }

    @Override
    public void exception(JoinPoint jp, Throwable throwable) {
        log.info("SysOut.exception(..): JoinPoint="+ jp+", Exception="+throwable);
    }
}
