package com.apress.prospring5.ch5.proxy_factory_bean;

import com.apress.prospring5.ch5.GrammyGuitarist;
import com.apress.prospring5.ch5.GrammyGuitaristInterface;
import org.springframework.context.support.GenericXmlApplicationContext;

public class ProxyFactoryBeanDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("ch5/app-context-xml.xml");
        context.refresh();

        GrammyGuitaristInterface proxyOne = context.getBean("proxyOne", GrammyGuitaristInterface.class);
        //GrammyGuitarist proxyTwo = context.getBean("proxyTwo", GrammyGuitarist.class);


        Documentarist documentaristOne = context.getBean("documentaristOne", Documentarist.class);
        Documentarist documentaristTwo = context.getBean("documentaristTwo", Documentarist.class);

        System.out.println("Documentarist One >> ");
        documentaristOne.execute();

        System.out.println("\nDocumentarist Two >> ");
        documentaristTwo.execute();
    }
}
