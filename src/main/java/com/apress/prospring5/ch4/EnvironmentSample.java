package com.apress.prospring5.ch4;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;
import java.util.Map;

public class EnvironmentSample {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.refresh();

        ConfigurableEnvironment environment = context.getEnvironment();
        MutablePropertySources propertySources = environment.getPropertySources();

        Map<String, Object> appMap = new HashMap<>();
        appMap.put("application.home", "application_name");

        propertySources.addLast(new MapPropertySource("prospring5_MAP", appMap));

        System.out.println("user.home: " + System.getProperty("user.home"));
        System.out.println("JAVA_HOME: " + System.getProperty("JAVA_HOME"));

        System.out.println("user.home: " + environment.getProperty("user.home"));
        System.out.println("JAVA_HOME: " + environment.getProperty("JAVA_HOME"));
        System.out.println("application.home: " + environment.getProperty("application.home"));


        context.close();
    }
}
