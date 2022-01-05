package com.apress.prospring5.ch3;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.util.StopWatch;

public class MethodReplacementDemo {
    public static void main(String... args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:ch3/app-context-method-replace.xml");
        context.refresh();

        ReplacementTarget replacementTarget = context.getBean("replacementTarget", ReplacementTarget.class);
        ReplacementTarget standardTarget = context.getBean("standardTarget", ReplacementTarget.class);

        displayInfo(replacementTarget);
        displayInfo(standardTarget);

        context.close();

    }

    private static void displayInfo(ReplacementTarget target) {
        System.out.println(target.formatMessage("Thanks for playing, try again!"));

        StopWatch stopWatch = new StopWatch();
        stopWatch.start("perfTest");

        for (int i = 0; i < 100_000; i++) {
            String out = target.formatMessage("No filter in my head");
        }

        stopWatch.stop();

        System.out.println("100000 invocations took: " + stopWatch.getTotalTimeMillis() + " ms");

    }
}
