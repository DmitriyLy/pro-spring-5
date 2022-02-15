package com.apress.prospring5.ch7;

import com.apress.prospring5.ch7.config.AdvancedConfig;
import com.apress.prospring5.ch7.dao.SingerDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class TableCreateDemo {
    public static void main(String[] args) {
        GenericApplicationContext context = new AnnotationConfigApplicationContext(AdvancedConfig.class);

        SingerDao singerDao = context.getBean(SingerDao.class);
        singerDao.findAll().forEach(System.out::println);

        context.close();
    }
}
