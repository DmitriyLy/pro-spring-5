package com.apress.prospring5.ch6.jdbc;

import com.apress.prospring5.ch6.config.EmbeddedJdbcConfig;
import com.apress.prospring5.ch6.dao.SingerDao;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

public class JdbcCfgTest {

    @Test
    public void testH2DB() {
        GenericApplicationContext context = new AnnotationConfigApplicationContext(EmbeddedJdbcConfig.class);
        SingerDao singerDao = context.getBean("singerDao", SingerDao.class);
        testDao(singerDao);
        context.close();
    }

    private void testDao(SingerDao singerDao) {
        assertNotNull(singerDao);
        String name = singerDao.findNameById(1L);
        assertTrue("John Mayer".endsWith(name));
    }
}
