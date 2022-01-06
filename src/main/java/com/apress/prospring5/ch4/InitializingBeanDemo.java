package com.apress.prospring5.ch4;

import org.springframework.context.support.GenericXmlApplicationContext;

public class InitializingBeanDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:ch4/app-context-xml.xml");
        context.refresh();

        SingerWithInterface singerOne = SingerWithInterface.getBean("singerOne", context);
        SingerWithInterface singerTwo = SingerWithInterface.getBean("singerTwo", context);
        SingerWithInterface singerThree = SingerWithInterface.getBean("singerThree", context);

        context.close();
    }
}
