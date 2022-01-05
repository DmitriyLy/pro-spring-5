package com.apress.prospring5.ch3.dependencies;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

public class DependsOnAnnotatedDemo {

    @Configuration
    @ComponentScan(basePackages = {"com.apress.prospring5.ch3.dependencies"})
    public static class DependsOnAnnotatedConfig {}

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DependsOnAnnotatedConfig.class);
        Singer johnMayer = context.getBean("johnMayer", Singer.class);
        johnMayer.sing();

        context.close();
    }
}
