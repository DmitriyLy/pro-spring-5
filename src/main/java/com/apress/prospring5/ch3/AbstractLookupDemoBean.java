package com.apress.prospring5.ch3;

public abstract class AbstractLookupDemoBean implements DemoBean {

    public abstract Singer getSinger();

    @Override
    public void doSomething() {
        getSinger().sing();
    }
}
