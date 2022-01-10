package com.apress.prospring5.ch4.boot.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @RequestMapping("/")
    public String sayHi() {
        return "Hello World!";
    }

}
