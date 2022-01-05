package com.apress.prospring5.ch3.config;

import com.apress.prospring5.ch3.annotated.DemoBean;
import com.apress.prospring5.ch3.annotated.Singer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.util.StopWatch;

public class LookupConfigDemo {

    @Configuration
    @ComponentScan(basePackages = {"com.apress.prospring5.ch3.annotated"})
    public static class LookupConfig {}

    public static void main(String[] args) {
        GenericApplicationContext context = new AnnotationConfigApplicationContext(LookupConfig.class);

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
