package com.apress.prospring5.ch6.rowmapper;

import com.apress.prospring5.ch6.config.NamedJdbcConfig;
import com.apress.prospring5.ch6.dao.SingerDao;
import com.apress.prospring5.ch6.entities.Album;
import com.apress.prospring5.ch6.entities.Singer;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RowMapperTest {

    @Test
    public void testRowMapper() {
        GenericApplicationContext context = new AnnotationConfigApplicationContext(NamedJdbcConfig.class);
        SingerDao singerDao = context.getBean(SingerDao.class);
        assertNotNull(singerDao);
        List<Singer> singers = singerDao.findAll();
        assertEquals(3, singers.size());
        singers.forEach(singer -> {
            System.out.println(singer);
            if (singer.getAlbums() != null) {
                for (Album album : singer.getAlbums()) {
                    System.out.println("------" + album);
                }
            }
        });
        context.close();
    }

}
