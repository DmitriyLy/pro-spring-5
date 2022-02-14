package com.apress.prospring5.ch7;

import com.apress.prospring5.ch7.config.AppConfig;
import com.apress.prospring5.ch7.dao.SingerDao;
import com.apress.prospring5.ch7.entities.Album;
import com.apress.prospring5.ch7.entities.Instrument;
import com.apress.prospring5.ch7.entities.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

public class SpringHibernateDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringHibernateDemo.class);

    public static void main(String[] args) {
        GenericApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        SingerDao singerDao = context.getBean(SingerDao.class);
        listSingers(singerDao.findAll());
        listSingersWithAlbum(singerDao.findAllWithAlbums());
        Singer singer = singerDao.findById(2L);
        LOGGER.info(singer.toString());
        context.close();
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

}
