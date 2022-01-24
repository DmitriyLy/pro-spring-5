package com.apress.prospring5.ch5.annotation_aspects;

import com.apress.prospring5.ch2.common.Guitar;
import com.apress.prospring5.ch5.GrammyGuitaristInterface;
import org.springframework.stereotype.Component;

@Component("johnMayer")
public class GrammyGuitarist implements GrammyGuitaristInterface {
    @Override
    public void sing() {
        System.out.println("sing: Gravity is working against me.\nAnd gravity wats to bring me down.");
    }

    public void sing(Guitar guitar) {
        System.out.println("play: " + guitar.play());
    }

    public void rest() {
        System.out.println("zzzzz");
    }

    public void talk() {
        System.out.println("talk");
    }
}
