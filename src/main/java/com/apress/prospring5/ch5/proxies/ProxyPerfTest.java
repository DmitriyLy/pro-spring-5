package com.apress.prospring5.ch5.proxies;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class ProxyPerfTest {
    public static void main(String[] args) {
        SimpleBean target = new DefaultSimpleBean();

        Advisor advisor = new DefaultPointcutAdvisor(new TestPointcut(), new NoOpBeforeAdvice());

        runCglibTests(advisor, target);
        runCglibFrozenTests(advisor, target);
        runJdkTests(advisor, target);
    }

    private static void runJdkTests(Advisor advisor, SimpleBean target) {
        ProxyFactory proxyFactory = getProxyFactory(advisor, target);
        proxyFactory.setInterfaces(SimpleBean.class);

        SimpleBean proxy = (SimpleBean) proxyFactory.getProxy();
        System.out.println("Running JDK Proxy Tests");
        test(proxy);
    }

    private static void runCglibFrozenTests(Advisor advisor, SimpleBean target) {
        ProxyFactory proxyFactory = getProxyFactory(advisor, target);
        proxyFactory.setProxyTargetClass(true);
        proxyFactory.setFrozen(true);

        SimpleBean proxy = (SimpleBean) proxyFactory.getProxy();
        System.out.println("Running CGLIB (Froze) Tests");
        test(proxy);
    }

    private static void runCglibTests(Advisor advisor, SimpleBean target) {
        ProxyFactory proxyFactory = getProxyFactory(advisor, target);
        proxyFactory.setProxyTargetClass(true);

        SimpleBean proxy = (SimpleBean) proxyFactory.getProxy();
        System.out.println("Running CGLIB (Standard) Tests");
        test(proxy);
    }

    private static ProxyFactory getProxyFactory(Advisor advisor, SimpleBean target) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvisor(advisor);
        return proxyFactory;
    }


    private static void test(SimpleBean bean) {
        long before = 0;
        long after = 0;

        System.out.println("Test Advised Method");
        before = System.currentTimeMillis();
        for (int i = 0; i < 500_000; i++) {
            bean.advised();
        }
        after = System.currentTimeMillis();
        System.out.println("Took " + (after - before) + " ms");

        System.out.println("Testing Unadvised Method");
        before = System.currentTimeMillis();
        for (int i = 0; i < 500_000; i++) {
            bean.unadvised();
            after = System.currentTimeMillis();
        }
        System.out.println("Took " + (after - before) + " ms");

        System.out.println("Testing equals() Method");
        before = System.currentTimeMillis();
        for (int i = 0; i < 500_000; i++) {
            boolean equals = bean.equals(bean);
            after = System.currentTimeMillis();
        }
        System.out.println("Took " + (after - before) + " ms");

        System.out.println("Testing hashCode() Method");
        before = System.currentTimeMillis();
        for (int i = 0; i < 500_000; i++) {
            int hashCode = bean.hashCode();
            after = System.currentTimeMillis();
        }
        System.out.println("Took " + (after - before) + " ms");

        Advised advised = (Advised) bean;

        System.out.println("Testing Advised.getTargetClass() Method");
        before = System.currentTimeMillis();
        for (int i = 0; i < 500_000; i++) {
            Class<?> targetClass = advised.getTargetClass();
            after = System.currentTimeMillis();
        }
        System.out.println("Took " + (after - before) + " ms");

        System.out.println(">>>>>\n");
    }
}
