package com.apress.prospring5.ch6.annotated;

import com.apress.prospring5.ch6.config.JdbcSingerDaoAnnotatedConfig2;
import com.apress.prospring5.ch6.dao.SingerDao;
import com.apress.prospring5.ch6.entities.Album;
import com.apress.prospring5.ch6.entities.Singer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

public class AnnotationJdbcTest {
    private GenericApplicationContext context;
    private SingerDao singerDao;

    @Before
    public void setUp() {
        context = new AnnotationConfigApplicationContext(JdbcSingerDaoAnnotatedConfig2.class);
        singerDao = context.getBean(SingerDao.class);
        assertNotNull(singerDao);
    }

    @Test
    public void testFindAll() {
        List<Singer> singers = singerDao.findAll();
        assertEquals(3, singers.size());
        listSingers(singers);
    }

    @Test
    public void testFindByFirstName() {
        List<Singer> singers = singerDao.findByFirstName("Eric");
        assertFalse(singers.isEmpty());
        listSingers(singers);
    }

    private void listSingers(List<Singer> singers) {
        singers.forEach(singer -> {
            System.out.println(singer);
            if (singer.getAlbums() != null) {
                for (Album album : singer.getAlbums()) {
                    System.out.println("\t--> " + album);
                }
            }
        });
    }

    @After
    public void tearDown() {
        context.close();
    }
}
