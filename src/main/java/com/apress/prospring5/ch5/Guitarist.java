package com.apress.prospring5.ch5;

import com.apress.prospring5.ch2.common.Guitar;
import com.apress.prospring5.ch2.common.Singer;
import com.apress.prospring5.ch5.annotation.AdviceRequired;

public class Guitarist implements Singer {

    private String lyric = "You are gonna live forever";

    @Override
    public void sing() {
        System.out.println(lyric);
    }

    public void sing2() {
        System.out.println("Just keep me where the light is");
    }

    public void rest() {
        System.out.println("zzzzzz");
    }

    @AdviceRequired
    public void sing(Guitar guitar) {
        System.out.println("play: " + guitar.play());
    }
}
