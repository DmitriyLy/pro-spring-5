package com.apress.prospring5.ch4;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;

public class SingerWithInterface implements InitializingBean {
    private static final String DEFAULT_NAME = "Eric Clapton";

    private String name;
    private int age = Integer.MIN_VALUE;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
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

    @Override
    public String toString() {
        return "SingerWithInterface{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static SingerWithInterface getBean(String beanName, ApplicationContext context) {
        try {
            SingerWithInterface bean = (SingerWithInterface) context.getBean(beanName);
            System.out.println(bean);
            return bean;
        } catch (BeanCreationException e) {
            System.out.println("An error in bean config: " + e.getMessage());
            return null;
        }
    }
}
