package com.zhiyin.logconf.ext.automon;

import com.github.nickvl.xspring.core.log.aop.SimpleLogAdapter;
import com.zhiyin.logconf.ext.automon.Slf4jImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.automon.aspects.AutomonSpringAspect;
import org.springframework.stereotype.Component;

/**
 * 扩展 autom
 * Created by wangqinghui on 2016/10/20.
 */
@Aspect
@Component
public class AutomonSpringAspectExt extends AutomonSpringAspect {


    public AutomonSpringAspectExt(){
        super();
        Slf4jImpl slf4jImpl = new Slf4jImpl();
        this.setOpenMon(slf4jImpl);
    }

    @Pointcut("@within(com.zhiyin.logconf.annotation.Logger) || @annotation(com.zhiyin.logconf.annotation.Logger)")
    public void annotatedClass() {
    }

    @Pointcut("  @annotation(com.zhiyin.logconf.annotation.LoggerIgnore)")
    public void ignore() {
    }

    @Around("annotatedClass() && !ignore()")
    public Object monitor(ProceedingJoinPoint pjp) throws Throwable {

        Object context = getOpenMon().start(pjp.getStaticPart());

        try {
            Object retVal = pjp.proceed();
            getOpenMon().stop(context);
            return retVal;
        } catch (Throwable throwable) {
            getOpenMon().stop(context, throwable);
            throw throwable;
        }
    }


}