package com.apress.prospring5.ch5.keycheck;

import org.springframework.aop.framework.ProxyFactory;

public class AfterAdviceDemo {

    private static KeyGenerator getKeyGenerator() {
        KeyGenerator target = new KeyGenerator();

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvice(new WeakKeyAdvice());

        return (KeyGenerator) proxyFactory.getProxy();
    }

    public static void main(String[] args) {
        KeyGenerator keyGenerator = getKeyGenerator();

        for (int i = 0; i < 10; i++ ) {
            try {
                long key = keyGenerator.getKey();
                System.out.println("Key: " + key);
            } catch (SecurityException e) {
                System.out.println("Weak Key Generated!");
            }
        }
    }

}
