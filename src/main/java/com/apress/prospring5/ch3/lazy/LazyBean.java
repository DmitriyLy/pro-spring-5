package com.apress.prospring5.ch3.lazy;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component("lazyBean")
@Lazy
public class LazyBean {
    public LazyBean() {
        System.out.println("Lazy bean constructor");
    }

    @PostConstruct
    public void init() {
        System.out.println("Lazy bean post construct");
    }
}
