package com.apress.prospring5.ch7.dao;

import com.apress.prospring5.ch7.entities.Instrument;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository("instrumentDao")
public class InstrumentDaoImpl implements InstrumentDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public InstrumentDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Instrument> findAll() {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from Instrument i")
                .list();
    }

    @Override
    public Instrument save(Instrument instrument) {
        sessionFactory.getCurrentSession().saveOrUpdate(instrument);
        return instrument;
    }
}
