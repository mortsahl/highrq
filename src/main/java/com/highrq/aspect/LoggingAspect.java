package com.highrq.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    boolean enteringCalled = false;

    public boolean isEnteringCalled() {
        return enteringCalled;
    }

//    @Before("execution(* com.highrq.core.services.AccountService.*(..))")
//    public void getBefore(JoinPoint joinPoint){
//        System.out.println("Before " + joinPoint.getStaticPart().getSignature().toString());
//    }
//
//    @After("execution(* com.highrq.core.services.AccountService.*(..))")
//    public void getAfter(JoinPoint joinPoint){
//        System.out.println("After " +  joinPoint.getStaticPart().getSignature().toString());
//    }

    @Before("execution(* com.highrq.core.services.AccountService.*(..))")
    public void getBefore(JoinPoint joinPoint) {
        enteringCalled = true;
        System.out.println("Before " +  joinPoint.getStaticPart().getSignature().toString());
    }

    // Commented out for current test

//    @After("execution(* com.highrq.core.services.AccountService.*(..))")
//    public void getAfter(JoinPoint joinPoint) {
//        enteringCalled = false;
//        System.out.println("After " +  joinPoint.getStaticPart().getSignature().toString());
//    }
}
