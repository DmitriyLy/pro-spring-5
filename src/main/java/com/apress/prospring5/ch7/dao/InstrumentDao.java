package com.apress.prospring5.ch7.dao;

import com.apress.prospring5.ch7.entities.Instrument;

import java.util.List;

public interface InstrumentDao {
    List<Instrument> findAll();
    Instrument save(Instrument instrument);
}
