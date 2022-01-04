package com.apress.prospring5.ch3;

public class StandardLookupDemoBean  implements DemoBean {
    private Singer singer;

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
