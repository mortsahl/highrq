package com.highrq.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class LoggingAspect {

    @Before("execution(* com.highrq.core.services.AccountService.*(..))")
    public void getAllAdvice(JoinPoint joinPoint){
        System.out.println("Before ***********************************" +joinPoint.toString());
    }
}
