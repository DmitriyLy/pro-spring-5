package com.apress.prospring5.ch5.annotation_aspects;

import com.apress.prospring5.ch2.common.Guitar;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AnnotatedAdvice {

    @Pointcut("execution(* com.apress.prospring5.ch5.annotation_aspects..sing*(com.apress.prospring5.ch2.common.Guitar)) && args(value)")
    public void singExecution(Guitar value) {
    }

    @Pointcut("bean(john*)")
    public void isJohn() {
    }

    @Before("singExecution(value) && isJohn()")
    public void simpleBeforeAdvice(JoinPoint joinPoint, Guitar value) {
        if ("Gibson".equals(value.getBrand())) {
            System.out.println("Executing: " +
                    joinPoint.getSignature().getDeclaringTypeName() + " " +
                    joinPoint.getSignature().getName() + " argument: " + value.getBrand());
        }
    }

    @Around("singExecution(value) && isJohn()")
    public Object simpleAroundAdvice(ProceedingJoinPoint proceedingJoinPoint, Guitar value) throws Throwable {
        System.out.println("Before execution: " + proceedingJoinPoint.getSignature().getDeclaringTypeName() + " " +
                proceedingJoinPoint.getSignature().getName() + " " +
                " argument: " + value.getBrand());

        Object retVal = proceedingJoinPoint.proceed();

        System.out.println("After execution: " + proceedingJoinPoint.getSignature().getDeclaringTypeName() + " " +
                proceedingJoinPoint.getSignature().getName() +
                " argument: " + value.getBrand());

        return retVal;
    }

}
