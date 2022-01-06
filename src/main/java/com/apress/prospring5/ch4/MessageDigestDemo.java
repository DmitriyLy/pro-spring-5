package com.apress.prospring5.ch4;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MessageDigestDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("ch4/app-context-xml-factory-bean.xml");
        context.refresh();

        MessageDigester digester = context.getBean("digester", MessageDigester.class);
        digester.digest("Hello World!");

        context.close();
    }
}
