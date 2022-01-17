package com.apress.prospring5.ch5.keycheck;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

import static com.apress.prospring5.ch5.keycheck.KeyGenerator.WEAK_KEY;

public class WeakKeyAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {

        if ((target instanceof KeyGenerator) && ("getKey".equals(method.getName()))) {
            long key = ((Long) returnValue).longValue();
            if (key == WEAK_KEY) {
                throw new SecurityException("Key Generator generated a weak key. Try again.");
            }
        }

    }
}
