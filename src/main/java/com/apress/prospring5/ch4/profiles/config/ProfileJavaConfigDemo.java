package com.apress.prospring5.ch4.profiles.config;

import com.apress.prospring5.ch4.profiles.Food;
import com.apress.prospring5.ch4.profiles.FoodProviderService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

public class ProfileJavaConfigDemo {
    public static void main(String[] args) {
        GenericApplicationContext context = new AnnotationConfigApplicationContext(KindergartenConfig.class, HighschoolConfig.class);

        FoodProviderService foodProviderService = context.getBean("foodProviderService", FoodProviderService.class);
        List<Food> foods = foodProviderService.provideLunchSet();
        foods.forEach(System.out::println);

        context.close();
    }
}
