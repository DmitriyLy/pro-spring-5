package com.apress.prospring5.ch4.profiles;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

public class ProfileXmlConfigExample {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        //context.getEnvironment().setActiveProfiles("kindergarten");
        context.getEnvironment().setActiveProfiles("highschool");
        context.load("ch4/*-config.xml");
        context.refresh();

        FoodProviderService foodProviderService = context.getBean("foodProviderService", FoodProviderService.class);
        List<Food> foods = foodProviderService.provideLunchSet();
        foods.forEach(System.out::println);

        context.close();
    }
}
