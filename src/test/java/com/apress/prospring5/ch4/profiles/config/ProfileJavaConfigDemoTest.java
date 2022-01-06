package com.apress.prospring5.ch4.profiles.config;

import com.apress.prospring5.ch4.profiles.FoodProviderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {KindergartenConfig.class, HighschoolConfig.class})
@ActiveProfiles("kindergarten")
public class ProfileJavaConfigDemoTest {

    @Autowired
    FoodProviderService foodProviderService;

    @Test
    public void testProvider() {
        assertNotNull(foodProviderService);
        assertNotNull(foodProviderService.provideLunchSet());
        assertFalse(foodProviderService.provideLunchSet().isEmpty());
        assertEquals(2, foodProviderService.provideLunchSet().size());
    }

}