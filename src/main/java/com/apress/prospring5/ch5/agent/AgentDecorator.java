package com.apress.prospring5.ch5.agent;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class AgentDecorator implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("James ");

        Object retVal = invocation.proceed();

        System.out.println("!");

        return retVal;
    }
}
