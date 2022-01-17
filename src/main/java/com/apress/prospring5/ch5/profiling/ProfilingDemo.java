package com.apress.prospring5.ch5.profiling;

import org.springframework.aop.framework.ProxyFactory;

public class ProfilingDemo {

    public static void main(String[] args) {
        WorkBean bean = getWorkBean();
        bean.doSomeWork(1000000);
    }

    private static WorkBean getWorkBean() {
        WorkBean target = new WorkBean();

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvice(new ProfilingInterceptor());

        return (WorkBean) proxyFactory.getProxy();
    }

}
