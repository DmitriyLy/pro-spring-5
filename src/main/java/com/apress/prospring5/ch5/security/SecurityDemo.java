package com.apress.prospring5.ch5.security;

import org.springframework.aop.framework.ProxyFactory;

public class SecurityDemo {
    public static void main(String[] args) {
        SecurityManager securityManager = new SecurityManager();

        SecureBean bean = getSecureBean();

        securityManager.login("John", "password");
        bean.writeSecureMessage();
        securityManager.logout();

        try {
            securityManager.login("unknown", "password");
            bean.writeSecureMessage();
        } catch (SecurityException e) {
            System.out.println("Exception Caught: " + e.getMessage());
        } finally {
            securityManager.logout();
        }

        try {
            bean.writeSecureMessage();
        } catch (SecurityException e) {
            System.out.println("Exception Caught: " + e.getMessage());
        }


    }

    private static SecureBean getSecureBean() {
        SecureBean secureBean = new SecureBean();

        SecurityAdvice advice = new SecurityAdvice();

        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(secureBean);
        factory.addAdvice(advice);

        SecureBean proxy = (SecureBean) factory.getProxy();

        return proxy;
    }
}
