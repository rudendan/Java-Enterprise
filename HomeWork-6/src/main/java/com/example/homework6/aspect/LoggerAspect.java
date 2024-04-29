package com.example.homework6.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.example.homework6.*.*.*(..))")
    public void loggingPointCut() {

    }

    @Around("loggingPointCut()")
    public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {

        String packageName = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        long start = System.currentTimeMillis();

        logger.info("Entering method [" + packageName + "." + methodName + "]");

        Object proceed = joinPoint.proceed();
        long elapsedTime = System.currentTimeMillis() - start;

        logger.info("Method [" + packageName + "." + methodName + "] was executed in " + elapsedTime + " ms");

        return proceed;
    }

}
