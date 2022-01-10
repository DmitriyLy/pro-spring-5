package com.apress.prospring5.ch5;

import com.apress.prospring5.ch2.common.Singer;

public class Guitarist implements Singer {

    private String lyric = "You are gonna live forever";

    @Override
    public void sing() {
        System.out.println(lyric);
    }
}
