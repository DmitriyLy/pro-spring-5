package com.apress.prospring5.ch5.security;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class SecurityAdvice implements MethodBeforeAdvice {
    private SecurityManager securityManager;

    public SecurityAdvice() {
        this.securityManager = new SecurityManager();
    }

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        UserInfo userInfo = securityManager.getLoggedOnUser();;

        if (userInfo == null) {
            System.out.println("No user authenticated");
            throw new SecurityException("You must login before attempting to invoke the method: " + method.getName());
        } else if ("John".equals(userInfo.getUserName())) {
            System.out.println("Logged in user is John - OKAY!");
        } else {
            System.out.println("Logged in user is " + userInfo.getUserName() + " NOT GOOD :(");
            throw new SecurityException("User " + userInfo.getUserName() + " is not allowed access to method " + method.getName());
        }
    }
}
