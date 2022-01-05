package com.apress.prospring5.ch3.annotated;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component("abstractLookupDemoBean")
public class AbstractLookupDemoBean implements DemoBean {

    @Lookup("singer")
    public Singer getSinger() {
        return null;
    }

    @Override
    public void doSomething() {
        getSinger().sing();
    }
}
