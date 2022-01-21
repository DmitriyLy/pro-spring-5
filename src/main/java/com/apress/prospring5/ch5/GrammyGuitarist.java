package com.apress.prospring5.ch5;

import com.apress.prospring5.ch2.common.Guitar;
import com.apress.prospring5.ch2.common.Singer;

public class GrammyGuitarist implements GrammyGuitaristInterface {
    @Override
    public void sing() {
        System.out.println("sing: Gravity is working against me\nAnd gravity wants to bring me down");
    }

    @Override
    public void sing(Guitar guitar) {
        System.out.println("play: " + guitar.play());
    }

    @Override
    public void rest() {
        System.out.println("zzzzzzzzzzzz");
    }

    @Override
    public void talk() {
        System.out.println("talk");
    }
}
