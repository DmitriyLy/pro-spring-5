package com.apress.prospring5.ch4.profiles.kindergarten;

import com.apress.prospring5.ch4.profiles.Food;
import com.apress.prospring5.ch4.profiles.FoodProviderService;

import java.util.ArrayList;
import java.util.List;

public class FoodProviderServiceImpl implements FoodProviderService {
    @Override
    public List<Food> provideLunchSet() {
        List<Food> foods = new ArrayList<>();
        foods.add(new Food("Milk"));
        foods.add(new Food("Biscuits"));

        return foods;
    }
}
