package com.apress.prospring5.ch5.annotation_aspects;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class AspectJAnnotationTest {

    @Test
    public void xmlTest() {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("ch5/app-context-component-scan.xml");
        context.refresh();

        NewDocumentarist documentarist = context.getBean("documentarist", NewDocumentarist.class);
        documentarist.execute();
        context.close();
    }

    @Test
    public void configTest() {
        GenericApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        NewDocumentarist documentarist = context.getBean("documentarist", NewDocumentarist.class);
        documentarist.execute();
        context.close();
    }
}
