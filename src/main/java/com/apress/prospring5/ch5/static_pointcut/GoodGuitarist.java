package com.apress.prospring5.ch5.static_pointcut;

import com.apress.prospring5.ch2.common.Singer;

public class GoodGuitarist implements Singer {
    @Override
    public void sing() {
        System.out.println("Who says I can;t be free \nFrom all of the things that I used to be");
    }
}
