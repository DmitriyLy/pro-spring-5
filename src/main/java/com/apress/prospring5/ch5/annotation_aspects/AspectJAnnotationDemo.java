package com.apress.prospring5.ch5.annotation_aspects;

import org.springframework.context.support.GenericXmlApplicationContext;

public class AspectJAnnotationDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("ch5/app-context-component-scan.xml");
        context.refresh();

        NewDocumentarist documentarist = context.getBean("documentarist", NewDocumentarist.class);
        documentarist.execute();

        context.close();
    }
}
