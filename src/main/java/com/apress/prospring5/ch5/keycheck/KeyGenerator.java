package com.apress.prospring5.ch5.keycheck;

import java.util.Random;

public class KeyGenerator {
    protected static final long WEAK_KEY = 0xFFFFFFF0000000L;
    protected static final long STRONG_KEY = 0xACDF03F590AE56L;

    private Random random = new Random();

    public long getKey() {
        int x = random.nextInt(3);

        return x == 1 ? WEAK_KEY : STRONG_KEY;

    }
}
