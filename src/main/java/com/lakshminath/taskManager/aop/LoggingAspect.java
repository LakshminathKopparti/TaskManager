package com.lakshminath.taskManager.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);
    @Before("execution(* com.lakshminath.taskManager.service..*(..))")
    public void logMethodCallWithArgs(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object[] args = joinPoint.getArgs();
        LOGGER.info("Service Method Called: {}.{} with arguments: {}", className, methodName, args);
    }

    @After("execution(* com.lakshminath.taskManager.service..*(..))")
    public void logMethodExecutedWithClass(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        LOGGER.info("Service Method executed: {}.{}", className, methodName);
    }

    @AfterThrowing(pointcut = "execution(* com.lakshminath.taskManager.service..*(..))", throwing = "ex")
    public void logServiceException(JoinPoint joinPoint, Throwable ex) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        LOGGER.error("Exception in Service Method: {}.{} - Exception: {}", className, methodName, ex.getMessage(), ex);
    }
}
