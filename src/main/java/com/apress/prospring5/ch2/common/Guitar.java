package com.apress.prospring5.ch2.common;

public class Guitar {
    private String brand;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String play() {
        return "G C G C Am D7";
    }
}
