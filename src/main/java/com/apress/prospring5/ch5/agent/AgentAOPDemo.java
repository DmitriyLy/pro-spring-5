package com.apress.prospring5.ch5.agent;

import org.springframework.aop.framework.ProxyFactory;

public class AgentAOPDemo {
    public static void main(String[] args) {
        Agent target = new Agent();

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvice(new AgentDecorator());
        proxyFactory.setTarget(target);

        Agent proxy = (Agent) proxyFactory.getProxy();
        target.speak();
        System.out.println(",");
        proxy.speak();
    }
}
