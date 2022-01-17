package com.apress.prospring5.ch5.profiling;

public class WorkBean {
    public void doSomeWork(int numberOfTimes) {
        for (int i = 0; i < numberOfTimes; i++) {
            work();
        }
    }

    private void work() {
        System.out.println("");
    }
}
