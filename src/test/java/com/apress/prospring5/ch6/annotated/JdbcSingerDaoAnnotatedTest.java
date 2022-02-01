package com.apress.prospring5.ch6.annotated;

import com.apress.prospring5.ch6.config.JdbcSingerDaoAnnotatedConfig;
import com.apress.prospring5.ch6.dao.JdbcSingerDaoAnnotated;
import com.apress.prospring5.ch6.dao.SingerDao;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import static org.junit.Assert.assertNotNull;

public class JdbcSingerDaoAnnotatedTest {

    @Test
    public void test() {
        GenericApplicationContext context = new AnnotationConfigApplicationContext(JdbcSingerDaoAnnotatedConfig.class);
        SingerDao bean = context.getBean(SingerDao.class);
        assertNotNull(((JdbcSingerDaoAnnotated) bean).getDataSource());
        context.close();
    }

}
