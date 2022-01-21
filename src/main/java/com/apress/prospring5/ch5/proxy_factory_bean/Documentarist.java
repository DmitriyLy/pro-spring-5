package com.apress.prospring5.ch5.proxy_factory_bean;

import com.apress.prospring5.ch5.GrammyGuitaristInterface;

public class Documentarist {

    private GrammyGuitaristInterface guitarist;

    public void execute() {
        guitarist.sing();
        guitarist.talk();
    }

    public void setGuitarist(GrammyGuitaristInterface guitarist) {
        this.guitarist = guitarist;
    }
}
