package com.apress.prospring5.ch12.services;

import com.apress.prospring5.ch12.entities.Singer;
import com.apress.prospring5.ch12.repositories.SingerRepository;
import com.apress.prospring5.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;

@Service
public class DBInitializer {
    private final static Logger LOGGER = LoggerFactory.getLogger(DBInitializer.class);

    @Autowired
    private SingerRepository singerRepository;

    @PostConstruct
    public void initDb() {
        LOGGER.info("Starting db init....");

        saveSinger("John", "Mayer", DateUtils.toDate(1977, 9, 16));
        saveSinger("Eric", "Clapton", DateUtils.toDate(1945, 1, 30));
        saveSinger("John", "Butler", DateUtils.toDate(1975, 3, 1));

        LOGGER.info("DB init completed.");
    }

    private void saveSinger(String firstName, String lastName, Date birthDate) {
        Singer singer = new Singer();
        singer.setFirstName(firstName);
        singer.setLastName(lastName);
        singer.setBirthDate(birthDate);
        singerRepository.save(singer);
    }
}
