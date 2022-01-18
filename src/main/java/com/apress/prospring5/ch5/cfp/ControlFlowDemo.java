package com.apress.prospring5.ch5.cfp;

import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ControlFlowPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class ControlFlowDemo {
    public static void main(String[] args) {
        ControlFlowDemo demo = new ControlFlowDemo();
        demo.run();
    }

    public void run() {
        TestBean target = new TestBean();
        Pointcut pointcut = new ControlFlowPointcut(ControlFlowDemo.class, "test");
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, new SimpleBeforeAdvice());

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvisor(advisor);

        TestBean proxy = (TestBean) proxyFactory.getProxy();
        System.out.println("\ttrying normal invoke");
        proxy.foo();

        System.out.println("\n\tTrying under ControlFlowDemo.test()");
        test(proxy);
    }

    private void test(TestBean bean) {
        bean.foo();
    }
}
