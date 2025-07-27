package com.lakshminath.taskManager.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ValidationAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationAspect.class);

    @Around("execution(* com.lakshminath.taskManager.service..*(..))")
    public Object validateAndUpdate(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();

        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof Integer) {
                int val = (Integer) args[i];
                if (val < 0) {
                    LOGGER.info("Negative value detected, converting to positive: {}", val);
                    args[i] = -val;
                }
            }
        }
        return pjp.proceed(args);
    }
}

