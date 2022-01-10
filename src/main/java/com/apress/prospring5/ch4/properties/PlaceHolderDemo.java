package com.apress.prospring5.ch4.properties;

import org.springframework.context.support.GenericXmlApplicationContext;

public class PlaceHolderDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("ch4/app-context-xml-properties.xml");
        context.refresh();

        AppProperty appProperty = context.getBean(AppProperty.class);

        System.out.println(appProperty);

        context.close();
    }
}
