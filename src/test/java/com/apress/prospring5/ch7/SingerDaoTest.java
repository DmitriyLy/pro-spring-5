package com.apress.prospring5.ch7;

import com.apress.prospring5.ch7.config.AppConfig;
import com.apress.prospring5.ch7.dao.SingerDao;
import com.apress.prospring5.ch7.entities.Album;
import com.apress.prospring5.ch7.entities.Instrument;
import com.apress.prospring5.ch7.entities.Singer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SingerDaoTest {
    private final static Logger LOGGER = LoggerFactory.getLogger(SingerDaoTest.class);

    private GenericApplicationContext context;
    private SingerDao singerDao;

    @Before
    public void setUp() {
        context = new AnnotationConfigApplicationContext(AppConfig.class);
        singerDao = context.getBean(SingerDao.class);
        assertNotNull(singerDao);
    }

    @Test
    public void testInsert() {
        Singer singer = new Singer();
        singer.setFirstName("BB");
        singer.setLastName("King");
        singer.setBirthDate(Date.from(
                LocalDate.of(1940, 8, 16)
                        .atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()
        ));

        Album album = new Album();
        album.setTitle("My Kind of Blues");
        album.setReleaseDate(java.sql.Date.valueOf(LocalDate.of(1962, 3, 20)));
        singer.addAlbum(album);

        singerDao.save(singer);
        assertNotNull(singer.getId());
        List<Singer> singers = singerDao.findAllWithAlbums();
        assertEquals(4, singers.size());
        listSingersWithAlbum(singers);
    }

    @Test
    public void testUpdate() {
        Singer singer = singerDao.findById(5L);
        assertNotNull(singer);
        assertEquals("King", singer.getLastName());
        singer.getAlbums().forEach(singer::removeAlbum);
        singerDao.save(singer);
        listSingersWithAlbum(singerDao.findAllWithAlbums());
    }

    @Test
    public void testDelete() {
        Singer singer = singerDao.findById(5L);
        assertNotNull(singer);
        singerDao.delete(singer);
        listSingersWithAlbum(singerDao.findAllWithAlbums());
    }

    private static void listSingers(List<Singer> singers) {
        LOGGER.info(" ---- Listing singers:");
        singers.stream()
                .map(Singer::toString)
                .forEach(LOGGER::info);
    }

    private static void listSingersWithAlbum(List<Singer> singers) {
        LOGGER.info(" ---- Listing singers with instruments:");
        for (Singer singer : singers) {
            LOGGER.info(singer.toString());
            if (singer.getAlbums() != null) {
                singer.getAlbums()
                        .stream()
                        .map(Album::toString)
                        .forEach(album -> LOGGER.info("\t" + album));
            }
            if (singer.getInstruments() != null) {
                singer.getInstruments().stream().map(Instrument::toString).forEach(s -> LOGGER.info("\t" + s));
            }
        }
    }

    @After
    public void tearDown() {
        context.close();
    }
}
