package com.apress.prospring5.ch5.static_pointcut;

import com.apress.prospring5.ch2.common.Singer;

public class GreatGuitarist implements Singer {
    @Override
    public void sing() {
        System.out.println("I shot the sheriff, \nBut I did not shoot the deputy");
    }
}
