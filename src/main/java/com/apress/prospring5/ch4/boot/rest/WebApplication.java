package com.apress.prospring5.ch4.boot.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication(scanBasePackageClasses = Controller.class)
public class WebApplication {
    private static Logger logger = LoggerFactory.getLogger(WebApplication.class);

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = SpringApplication.run(WebApplication.class, args);

        assert (context != null);

        logger.info("Application started...");

        System.in.read();

        context.close();
    }
}
