package com.apress.prospring5.ch5.introduction;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class IsModifiedMixin extends DelegatingIntroductionInterceptor implements IsModified {
    private boolean isModified = false;
    private Map<Method, Method> methodCache = new HashMap<>();

    @Override
    public boolean isModified() {
        return isModified;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        if (!isModified) {
            if (isSetter(invocation)) {
                Method getter = getGetter(invocation.getMethod());

                if (getter != null) {
                    Object newVal = invocation.getArguments()[0];
                    Object oldVal = getter.invoke(invocation.getThis(), null);
                    isModified = detectIsModified(newVal, oldVal);

                }

            }
        }

        return super.invoke(invocation);
    }

    private boolean detectIsModified(Object newVal, Object oldVal) {
        if ((newVal == null) && (oldVal == null)) {
            return false;
        }

        if ((newVal == null) && (oldVal != null)) {
            return true;
        }

        if ((newVal != null) && (oldVal == null)) {
            return true;
        }

        return isModified = !newVal.equals(oldVal);
    }

    private boolean isSetter(MethodInvocation invocation) {
        return invocation.getMethod().getName().startsWith("set") && (invocation.getArguments().length == 1);
    }

    private Method getGetter(Method setter) {
        Method getter = methodCache.get(setter);

        if (getter != null) {
            return getter;
        }

        String getterName = setter.getName().replaceFirst("set", "get");
        try {
            getter = setter.getDeclaringClass().getMethod(getterName, null);
            synchronized (methodCache) {
                methodCache.put(setter, getter);
            }
            return getter;
        } catch (NoSuchMethodException e) {
            return null;
        }
    }
}
