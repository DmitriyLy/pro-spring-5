package com.apress.prospring5.ch5.annotation;

import com.apress.prospring5.ch2.common.Guitar;
import com.apress.prospring5.ch5.Guitarist;
import com.apress.prospring5.ch5.static_pointcut.SimpleAdvice;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;

public class AnnotationPointcutDemo {
    public static void main(String[] args) {
        Guitarist johnMayer = new Guitarist();

        AnnotationMatchingPointcut pointcut = AnnotationMatchingPointcut.forMethodAnnotation(AdviceRequired.class);
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, new SimpleAdvice());

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(johnMayer);
        proxyFactory.addAdvisor(advisor);
        Guitarist proxy = (Guitarist) proxyFactory.getProxy();

        proxy.sing(new Guitar());
        proxy.rest();
    }
}
