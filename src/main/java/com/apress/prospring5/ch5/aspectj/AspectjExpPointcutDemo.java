package com.apress.prospring5.ch5.aspectj;

import com.apress.prospring5.ch5.Guitarist;
import com.apress.prospring5.ch5.static_pointcut.SimpleAdvice;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class AspectjExpPointcutDemo {
    public static void main(String[] args) {
        Guitarist johnMayer = new Guitarist();

        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* sing*(..))");
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, new SimpleAdvice());

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(johnMayer);
        proxyFactory.addAdvisor(advisor);
        Guitarist proxy = (Guitarist) proxyFactory.getProxy();

        proxy.sing();
        proxy.sing2();
        proxy.rest();
    }
}
