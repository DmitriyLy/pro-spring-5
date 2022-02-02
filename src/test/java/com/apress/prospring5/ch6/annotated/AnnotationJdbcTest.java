package com.apress.prospring5.ch6.annotated;

import com.apress.prospring5.ch6.config.EmbeddedJdbcConfig2;
import com.apress.prospring5.ch6.config.JdbcSingerDaoAnnotatedConfig2;
import com.apress.prospring5.ch6.dao.SingerDao;
import com.apress.prospring5.ch6.entities.Album;
import com.apress.prospring5.ch6.entities.Singer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class AnnotationJdbcTest {
    private GenericApplicationContext context;
    private SingerDao singerDao;

    @Before
    public void setUp() {
        context = new AnnotationConfigApplicationContext(EmbeddedJdbcConfig2.class);
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

    @Test
    public void testSingerUpdate() {
        Singer singer = new Singer();
        singer.setId(1L);
        singer.setFirstName("John Clayton");
        singer.setLastName("Mayer");
        singer.setBirthDate(Date.valueOf(LocalDate.of(1977, 9, 16)));
        singerDao.update(singer);

        List<Singer> singers = singerDao.findAll();
        listSingers(singers);
    }

    @Test
    public void testSingerInsert() {
        Singer singer = new Singer();
        singer.setFirstName("Ed");
        singer.setLastName("Shmed");
        singer.setBirthDate(Date.valueOf(LocalDate.of(1991, 1, 17)));
        singerDao.insert(singer);

        listSingers(singerDao.findAll());
    }

    @Test
    public void testSingerInsertWithAlbum() {
        Singer singer = new Singer();
        singer.setFirstName("BB");
        singer.setLastName("King");
        singer.setBirthDate(Date.valueOf(LocalDate.of(1940, 8, 16)));

        singer.addAlbum(() -> {
            Album album = new Album();
            album.setTitle("My Kind of Blues");
            album.setReleaseDate(Date.valueOf(LocalDate.of(1961, 7, 18)));
            return album;
        });

        singer.addAlbum(() -> {
            Album album = new Album();
            album.setTitle("A Heart Full of Blues");
            album.setReleaseDate(Date.valueOf(LocalDate.of(1962, 3, 20)));
            return album;
        });

        singerDao.insertWithAlbum(singer);

        listSingers(singerDao.findAllWithAlbums());
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
