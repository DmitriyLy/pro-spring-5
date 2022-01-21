package com.apress.prospring5.ch5;

import com.apress.prospring5.ch2.common.Guitar;
import com.apress.prospring5.ch2.common.Singer;

public interface GrammyGuitaristInterface extends Singer {
    void sing(Guitar guitar);

    void rest();

    void talk();
}
