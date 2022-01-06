package com.apress.prospring5.ch4;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Singer {
    private static final String DEFAULT_NAME = "Eric Clapton";

    private String name;
    private int age = Integer.MIN_VALUE;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void init() {
        System.out.println("Initializing bean");

        if (name == null) {
            System.out.println("Using default name");
            name = DEFAULT_NAME;
        }

        if (age == Integer.MIN_VALUE) {
            throw new IllegalArgumentException(
                    "You must set age property of any beans of type " + SingerWithInterface.class
            );
        }
    }

    public String toString() {
        return "\tName: " + name + "\n\tAge: " + age;
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext("classpath:ch4/app-context-xml1.xml");
        getBean("singerOne", context);
        getBean("singerTwo", context);
        getBean("singerThree", context);
    }

    public static Singer getBean(String beanName, ApplicationContext context) {
        try {
            Singer bean = (Singer) context.getBean(beanName);
            System.out.println(bean);
            return bean;
        } catch (BeanCreationException e) {
            System.out.println("An error in bean config: " + e.getMessage());
            return null;
        }
    }
}
