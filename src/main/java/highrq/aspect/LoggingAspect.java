package highrq.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class LoggingAspect {

    @Before("execution(* * (..))")
    public void getAllAdvice(){
        System.out.println("Before");
    }
}
