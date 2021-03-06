package com.apress.prospring5.ch8.service;

import com.apress.prospring5.ch8.entities.Singer;
import com.apress.prospring5.ch8.metamodel.Singer_;
import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
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
        return entityManager.createNamedQuery(Singer.FIND_ALL_WITH_ALBUM, Singer.class).getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public Singer findById(Long id) {
        TypedQuery<Singer> query = entityManager.createNamedQuery(Singer.FIND_SINGER_BY_ID, Singer.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public Singer save(Singer singer) {
        if (singer.getId() == null) {
            LOGGER.info("Inserting new singer");
            entityManager.persist(singer);
        } else {
            entityManager.merge(singer);
            LOGGER.info("Updating existing singer");
        }
        LOGGER.info("Singer saved with id: " + singer.getId());
        return singer;
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

    @Override
    public List<Singer> findByCriteriaQuery(String firstName, String lastName) {
        LOGGER.info("Searching singers for firstName: {} and lastName: {}", firstName, lastName);

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Singer> criteriaQuery = criteriaBuilder.createQuery(Singer.class);
        Root<Singer> singerRoot = criteriaQuery.from(Singer.class);
        singerRoot.fetch(Singer_.albums, JoinType.LEFT);
        singerRoot.fetch(Singer_.instruments, JoinType.LEFT);

        criteriaQuery.select(singerRoot).distinct(true);

        Predicate criteria = criteriaBuilder.conjunction();

        if (firstName != null) {
            criteria = criteriaBuilder.and(criteria,
                    criteriaBuilder.equal(singerRoot.get(Singer_.firstName), firstName));
        }

        if (lastName != null) {
            criteria = criteriaBuilder.and(criteria,
                    criteriaBuilder.equal(singerRoot.get(Singer_.lastName), lastName));
        }

        criteriaQuery.where(criteria);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
