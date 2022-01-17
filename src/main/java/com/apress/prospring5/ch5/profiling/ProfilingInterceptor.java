package com.apress.prospring5.ch5.profiling;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;

public class ProfilingInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start(invocation.getMethod().getName());

        Object returnValue = invocation.proceed();

        stopWatch.stop();

        dumpInfo(invocation, stopWatch.getTotalTimeMillis());

        return returnValue;
    }

    private void dumpInfo(MethodInvocation invocation, long totalTimeMillis) {
        Method method = invocation.getMethod();
        Object target = invocation.getThis();
        Object[] arguments = invocation.getArguments();

        System.out.println("Executed method: " + method.getName());
        System.out.println("On object of type: " + target.getClass().getName());

        System.out.println("With arguments: ");
        for (int i = 0; i < arguments.length; i++) {
            System.out.println("\t" + arguments[i]);
        }
        System.out.println("\n");
        System.out.println("Took: " + totalTimeMillis + " ms");
    }
}
