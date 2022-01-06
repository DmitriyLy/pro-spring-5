package com.apress.prospring5.ch4.profiles.highschool;

import com.apress.prospring5.ch4.profiles.Food;
import com.apress.prospring5.ch4.profiles.FoodProviderService;

import java.util.ArrayList;
import java.util.List;

public class FoodProviderServiceImpl implements FoodProviderService {
    @Override
    public List<Food> provideLunchSet() {
        List<Food> foods = new ArrayList<>();
        foods.add(new Food("Coke"));
        foods.add(new Food("Hamburger"));
        foods.add(new Food("French Fries"));

        return foods;
    }
}
