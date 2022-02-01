package com.apress.prospring5.ch6.named;

import com.apress.prospring5.ch6.config.NamedJdbcConfig;
import com.apress.prospring5.ch6.dao.SingerDao;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class NamedJdbcConfigTest {

    @Test
    public void testNamedJdbcTemplate() {
        GenericApplicationContext context = new AnnotationConfigApplicationContext(NamedJdbcConfig.class);
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
