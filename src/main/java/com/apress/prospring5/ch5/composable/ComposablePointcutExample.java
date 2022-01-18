package com.apress.prospring5.ch5.composable;

import com.apress.prospring5.ch2.common.Guitar;
import com.apress.prospring5.ch5.cfp.SimpleBeforeAdvice;
import org.springframework.aop.Advisor;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcher;

import java.lang.reflect.Method;

public class ComposablePointcutExample {
    public static void main(String[] args) {
        GrammyGuitarist johnMayer = new GrammyGuitarist();

        ComposablePointcut pointcut = new ComposablePointcut(ClassFilter.TRUE, new SingMethodMatcher());

        System.out.println("Test 1 >>> ");
        GrammyGuitarist proxy = getProxy(pointcut, johnMayer);
        testInvoke(proxy);
        System.out.println();

        System.out.println("Test 2 >>> ");
        pointcut.union(new TalkMethodMatcher());
        proxy = getProxy(pointcut, johnMayer);
        testInvoke(proxy);
        System.out.println();

        System.out.println("Test 3 >>> ");
        pointcut.intersection(new RestMethodMatcher());
        proxy = getProxy(pointcut, johnMayer);
        testInvoke(proxy);
    }

    private static GrammyGuitarist getProxy(ComposablePointcut pointcut, GrammyGuitarist target) {
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, new SimpleBeforeAdvice());

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvisor(advisor);
        return (GrammyGuitarist) proxyFactory.getProxy();
    }

    private static void testInvoke(GrammyGuitarist proxy) {
        proxy.sing();
        proxy.sing(new Guitar());
        proxy.talk();
        proxy.rest();
    }

    private static class SingMethodMatcher extends StaticMethodMatcher {
        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            return (method.getName().startsWith("si"));
        }
    }

    private static class TalkMethodMatcher extends StaticMethodMatcher {
        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            return "talk".equals(method.getName());
        }
    }

    private static class RestMethodMatcher extends StaticMethodMatcher {
        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            return (method.getName().endsWith("st"));
        }
    }
}
