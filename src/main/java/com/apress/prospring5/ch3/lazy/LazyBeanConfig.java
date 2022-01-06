package com.apress.prospring5.ch3.lazy;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.apress.prospring5.ch3.lazy"})
public class LazyBeanConfig {
    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LazyBeanConfig.class);
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LazyBean lazyBean = context.getBean("lazyBean", LazyBean.class);
        });
        thread.start();
        System.out.println("thead created");
        thread.join();
        context.close();
    }
}
