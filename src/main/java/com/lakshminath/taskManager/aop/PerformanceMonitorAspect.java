package com.lakshminath.taskManager.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PerformanceMonitorAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(PerformanceMonitorAspect.class);
    @Around("execution(* com.lakshminath.taskManager.service..*(..))")
    public Object monitorTime(ProceedingJoinPoint pjp) throws Throwable {
        Long start = System.currentTimeMillis();
      Object obj =   pjp.proceed();
        Long end = System.currentTimeMillis();
        LOGGER.info("Time Taken by method:"+ pjp.getSignature().getName()+ " is " + (end-start) + " ms");
        return obj;
    }
}
