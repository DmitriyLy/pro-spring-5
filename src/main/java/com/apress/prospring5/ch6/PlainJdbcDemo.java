package com.apress.prospring5.ch6;

import com.apress.prospring5.ch6.dao.PlainSingerDao;
import com.apress.prospring5.ch6.dao.SingerDao;
import com.apress.prospring5.ch6.entities.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class PlainJdbcDemo {
    private static SingerDao singerDao = new PlainSingerDao();
    private static Logger logger = LoggerFactory.getLogger(PlainJdbcDemo.class);

    public static void main(String[] args) {
        logger.info("Listing initial singer data");
        listAllSingers();

        logger.info("--------------");
        logger.info("Insert a new singer");

        Singer singer = new Singer();
        singer.setFirstName("Ed");
        singer.setLastName("Shmed");
        singer.setBirthDate(Date.valueOf(LocalDate.of(1914, 4, 3)));
        singerDao.insert(singer);

        logger.info("Listing singers after insert");
        listAllSingers();

        logger.info("--------------");
        logger.info("Deleting inserted singer");

        singerDao.delete(singer.getId());

        logger.info("Listing singers after deletion");
        listAllSingers();
    }

    private static void listAllSingers() {
        List<Singer> singers = singerDao.findAll();
        singers.forEach(singer -> logger.info(singer.toString()));
    }
}
