package com.apress.prospring5.ch5;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

public class SimpleBeforeAdvice implements MethodBeforeAdvice {

    public static void main(String[] args) {
        Guitarist johnMayer = new Guitarist();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvice(new SimpleBeforeAdvice());
        proxyFactory.setTarget(johnMayer);

        Guitarist proxy = (Guitarist) proxyFactory.getProxy();
        proxy.sing();
    }

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("Before '" + method.getName() + "', tune guitar");
    }
}
