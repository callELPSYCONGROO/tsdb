package com.bici.tsdb.datahandling.aop;

import com.bici.tsdb.common.util.FileUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * LogAspct
 * @author: Overload
 * @date: 2018/5/3 10:25
 * @version: 1.0
 */
@Aspect
@Component
public class LogAspct {

    @Pointcut("execution(public * com.bici.tsdb.datahandling.handle.DataListener.processMessage(..))")
    public void pointcut() {}

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pjp) {
        long start = System.nanoTime();
        Object proceed;
        try {
            proceed = pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
        long end = System.nanoTime();
        long time = end - start;
        FileUtil.write(3, time);
        FileUtil.write(1, 0);
        return proceed;
    }

    @AfterThrowing(value = "pointcut()")
    public void afterThrowing() {
        FileUtil.write(2, 0);
    }
}
