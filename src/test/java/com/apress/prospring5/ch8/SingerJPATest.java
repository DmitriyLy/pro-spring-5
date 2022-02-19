package com.apress.prospring5.ch8;

import com.apress.prospring5.ch8.config.JpaConfig;
import com.apress.prospring5.ch8.entities.Album;
import com.apress.prospring5.ch8.entities.Instrument;
import com.apress.prospring5.ch8.entities.Singer;
import com.apress.prospring5.ch8.service.SingerService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SingerJPATest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SingerJPATest.class);

    private GenericApplicationContext context;
    private SingerService singerService;

    @Before
    public void setUp() {
        context = new AnnotationConfigApplicationContext(JpaConfig.class);
        singerService = context.getBean(SingerService.class);
        assertNotNull(singerService);
    }

    @Test
    public void testFindAll() {
        List<Singer> singers = singerService.findAll();
        assertEquals(3, singers.size());
        listSingers(singers);
    }

    @Test
    public void testFindAllWithAlbum() {
        List<Singer> singers = singerService.findAllWithAlbum();
        assertEquals(3, singers.size());
        listSingersWithAlbum(singers);
    }

    @Test
    public void testFindById() {
        Singer singer = singerService.findById(1L);
        assertNotNull(singer);
        assertEquals("John", singer.getFirstName());
        assertEquals("Mayer", singer.getLastName());
        System.out.println(singer);
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
