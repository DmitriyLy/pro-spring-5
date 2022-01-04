package com.apress.prospring5.ch3;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.util.StopWatch;

public class LookupDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:ch3/app-context-xml.xml");
        context.refresh();

        DemoBean abstractBean = context.getBean("abstractLookupDemoBean", DemoBean.class);
        DemoBean standardBean = context.getBean("standardLookupDemoBean", DemoBean.class);

        displayInfo("abstractLookupBean", abstractBean);
        displayInfo("standardLookupBean", standardBean);

        context.close();
    }

    public static void displayInfo(String beanMane, DemoBean bean) {
        Singer singer1 = bean.getSinger();
        Singer singer2 = bean.getSinger();

        System.out.println("" + beanMane + ": Singer Instances the Same? " + (singer1 == singer2));

        StopWatch stopWatch = new StopWatch();
        stopWatch.start("lookupDemo");
        for (int x = 0; x < 100000; x++) {
            Singer singer = bean.getSinger();
            singer.sing();
        }

        stopWatch.stop();
        System.out.println("100000 gets took " + stopWatch.getTotalTimeMillis() + " ms") ;
    }
}
