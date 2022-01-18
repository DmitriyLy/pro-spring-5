package com.apress.prospring5.ch5.proxies;

import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

public class TestPointcut extends StaticMethodMatcherPointcut {
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return ("advised".equals(method.getName()));
    }
}
