package com.apress.prospring5.ch8.service;

import com.apress.prospring5.ch8.entities.Singer;
import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service("jpaSingerService")
@Repository
@Transactional
public class SingerServiceImpl implements SingerService {

    private static final String ALL_SINGER_NATIVE_QUERY = "select id, first_name, last_name, birth_date, version from singer";
    private static final Logger LOGGER = LoggerFactory.getLogger(SingerServiceImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public List<Singer> findAll() {
        return entityManager.createNamedQuery(Singer.FIND_ALL, Singer.class).getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Singer> findAllWithAlbum() {
        throw new NotImplementedException("findAllWithAlbum");
    }

    @Transactional(readOnly = true)
    @Override
    public Singer findById(Long id) {
        throw new NotImplementedException("findById");
    }

    @Override
    public Singer save(Singer singer) {
        throw new NotImplementedException("save");
    }

    @Override
    public void delete(Singer singer) {
        throw new NotImplementedException("delete");
    }

    @Transactional(readOnly = true)
    @Override
    public List<Singer> findAllByNativeQuery() {
        throw new NotImplementedException("findAllByNativeQuery");
    }
}
