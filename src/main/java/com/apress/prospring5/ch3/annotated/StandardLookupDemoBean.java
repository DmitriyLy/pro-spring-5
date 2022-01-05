package com.apress.prospring5.ch3.annotated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("standardLookupDemoBean")
public class StandardLookupDemoBean implements DemoBean {
    private Singer singer;

    @Autowired
    @Qualifier("singer")
    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    @Override
    public Singer getSinger() {
        return singer;
    }

    @Override
    public void doSomething() {
        singer.sing();
    }
}
