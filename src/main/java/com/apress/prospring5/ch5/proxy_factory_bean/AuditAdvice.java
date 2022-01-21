package com.apress.prospring5.ch5.proxy_factory_bean;

import org.aopalliance.aop.Advice;
import org.aspectj.lang.JoinPoint;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class AuditAdvice implements MethodBeforeAdvice {
    public void simpleBeforeAdvice(JoinPoint joinPoint) {
        System.out.println("Executing: " + joinPoint.getSignature().getDeclaringTypeName()
                + " " + joinPoint.getSignature().getName());
    }

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("Executing: " + method.getReturnType().getName() + " "
                + target.getClass().getName()  + "." + method.getName() + "()");
    }
}
