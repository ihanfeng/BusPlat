package com.zhiyin.logconf.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LoggerAspect {

    private boolean enabled = true;

    @Pointcut("@annotation(com.zhiyin.logconf.annotation.Logger)")
    public void annotatedClass() {
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Around("annotatedClass()")
    public Object logAndExecute(ProceedingJoinPoint joinPoint) throws Throwable {

        String targetClsName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();

        System.out.println(getMethodSignatureAndParameterValues(joinPoint));

        Object result = null;
        StopWatch stopWatch = new StopWatch();

        try {
            stopWatch.start();

            result = joinPoint.proceed();

            stopWatch.stop();

        } catch (Throwable error) {
            result = " error: " + error.getMessage();
        }

        System.out.println(getResult(joinPoint, result, stopWatch.getTotalTimeMillis()));

        return result;
    }

    private String getMethodSignatureAndParameterValues(JoinPoint joinPoint) {
        if (!enabled) return "";

        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();

        Class<?> cls = codeSignature.getDeclaringType();
        String targetClsName = joinPoint.getTarget().getClass().getName();
        String methodName = codeSignature.getName();
        String[] parameterNames = codeSignature.getParameterNames();
        Object[] parameterValues = joinPoint.getArgs();

        StringBuilder builder = new StringBuilder(targetClsName)
                .append(".")
                .append(methodName)
                .append('(');
        for (int i = 0; i < parameterValues.length; i++) {
            if (i > 0) builder.append(", ");
            builder.append(parameterNames[i]).append('=');
            builder.append(parameterValues[i]);
        }
        builder.append(')');

        return builder.toString();
    }

    private String getResult(JoinPoint joinPoint, Object result, long lengthMillis) {
        if (!enabled) return "";

        Signature signature = joinPoint.getSignature();

        Class<?> cls = signature.getDeclaringType();
        String targetClsName = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        boolean hasReturnType = signature instanceof MethodSignature
                && ((MethodSignature) signature).getReturnType() != void.class;

        StringBuilder builder = new StringBuilder(targetClsName)
                .append(".")
                .append(methodName)
                .append(" [")
                .append(lengthMillis)
                .append("ms]");

        if (hasReturnType) {
            builder.append(" = ");
            builder.append(result);
        }

        return builder.toString();
    }
}
