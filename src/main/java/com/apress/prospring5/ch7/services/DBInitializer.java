package com.apress.prospring5.ch7.services;

import com.apress.prospring5.ch7.dao.InstrumentDao;
import com.apress.prospring5.ch7.dao.SingerDao;
import com.apress.prospring5.ch7.entities.Album;
import com.apress.prospring5.ch7.entities.Instrument;
import com.apress.prospring5.ch7.entities.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
public class DBInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(DBInitializer.class);

    @Autowired
    private SingerDao singerDao;

    @Autowired
    private InstrumentDao instrumentDao;

    @PostConstruct
    public void initDB() {
        LOGGER.info("Starting database initialization...");

        Singer singer = new Singer();
        singer.setFirstName("John");
        singer.setLastName("Mayer");
        singer.setBirthDate(Date.from(LocalDate.of(1977, 9, 16)
                .atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant()));
        singer.addInstrument(addInstrument("Guitar"));
        singer.addInstrument(addInstrument("Piano"));
        singer.addAlbum(addAlbum("Battle Studies", LocalDate.of(2009, 10, 17)));

        singerDao.save(singer);

        LOGGER.info("Database init finished.");
    }

    private Album addAlbum(String title, LocalDate releaseDate) {
        Album album = new Album();
        album.setTitle(title);
        album.setReleaseDate(java.sql.Date.valueOf(releaseDate));
        return album;
    }

    private Instrument addInstrument(String instrumentId) {
        Instrument instrument = new Instrument();
        instrument.setInstrumentId(instrumentId);
        instrumentDao.save(instrument);
        return instrument;
    }

}
