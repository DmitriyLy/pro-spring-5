package com.apress.prospring5.ch4;

import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MessageDigestFactoryDemo {
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericXmlApplicationContext("ch4/app-context-xml-factory-bean1.xml");
        MessageDigester digester = context.getBean("digester", MessageDigester.class);
        digester.digest("Hello  World!");
        context.close();
    }
}
