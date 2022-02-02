package com.apress.prospring5.ch6.dao;

import com.apress.prospring5.ch6.entities.Album;
import com.apress.prospring5.ch6.extractor.SingerWithDetailExtractor;
import com.apress.prospring5.ch6.operation.*;
import com.apress.prospring5.ch6.entities.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository("singerDao")
public class JdbcSingerDaoAnnotated implements SingerDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcSingerDaoAnnotated.class);
    private static final String SQL_FIND_ALL_WITH_ALBUMS = "SELECT s.id, s.first_name, s.last_name, s.birth_date, " +
            "a.id as album_id, a.title, a.release_date FROM singer s LEFT JOIN album a ON s.id = a.singer_id";

    private DataSource dataSource;
    private SelectAllSingersOperation selectAllSingersOperation;
    private SelectSingerByFirstNameOperation selectSingerByFirstNameOperation;
    private UpdateSingerOperation updateSingerOperation;
    private InsertSingerOperation insertSingerOperation;
    private InsertSingerAlbumOperation insertSingerAlbumOperation;
    private  JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void init() {
        jdbcTemplate = new JdbcTemplate(getDataSource());
    }

    @Override
    public List<Singer> findAllWithAlbums() {
        if (jdbcTemplate == null) {
            throw new RuntimeException("JdbcTemplate is not initialize");
        }
        return jdbcTemplate.query(SQL_FIND_ALL_WITH_ALBUMS, new SingerWithDetailExtractor());
    }

    public InsertSingerAlbumOperation getInsertSingerAlbumOperation() {
        return insertSingerAlbumOperation;
    }

    @Autowired
    public void setInsertSingerAlbumOperation(InsertSingerAlbumOperation insertSingerAlbumOperation) {
        this.insertSingerAlbumOperation = insertSingerAlbumOperation;
    }

    public InsertSingerOperation getInsertSingerOperation() {
        return insertSingerOperation;
    }

    @Autowired
    public void setInsertSingerOperation(InsertSingerOperation insertSingerOperation) {
        this.insertSingerOperation = insertSingerOperation;
    }

    public UpdateSingerOperation getUpdateSingerOperation() {
        return updateSingerOperation;
    }

    @Autowired
    public void setUpdateSingerOperation(UpdateSingerOperation updateSingerOperation) {
        this.updateSingerOperation = updateSingerOperation;
    }

    public SelectSingerByFirstNameOperation getSelectSingerByFirstNameOperation() {
        return selectSingerByFirstNameOperation;
    }

    @Autowired
    public void setSelectSingerByFirstNameOperation(SelectSingerByFirstNameOperation selectSingerByFirstNameOperation) {
        this.selectSingerByFirstNameOperation = selectSingerByFirstNameOperation;
    }

    public SelectAllSingersOperation getSelectAllSingersOperation() {
        return selectAllSingersOperation;
    }

    @Resource(name = "selectAllSingersOperation")
    public void setSelectAllSingersOperation(SelectAllSingersOperation selectAllSingersOperation) {
        this.selectAllSingersOperation = selectAllSingersOperation;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Singer> findAll() {
        return selectAllSingersOperation.execute();
    }

    @Override
    public List<Singer> findByFirstName(String firstName) {
        Map<String, Object> params = new HashMap<>();
        params.put("first_name", firstName);
        return selectSingerByFirstNameOperation.executeByNamedParam(params);
    }

    @Override
    public String findLastNameById(Long id) {
        return null;
    }

    @Override
    public String findFirstNameById(Long id) {
        return null;
    }

    @Override
    public void insert(Singer singer) {
        Map<String, Object> params = getSingerParams(singer);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        insertSingerOperation.updateByNamedParam(params, keyHolder);

        singer.setId(keyHolder.getKey().longValue());

        LOGGER.info("New singer inserted with id: " + singer.getId());
    }

    @Override
    public void update(Singer singer) {
        Map<String, Object> params = new HashMap<>();
        params.put("first_name", singer.getFirstName());
        params.put("last_name", singer.getLastName());
        params.put("birth_date", singer.getBirthDate());
        params.put("id", singer.getId());
        updateSingerOperation.updateByNamedParam(params);
        LOGGER.info("Existing singer updated with id: " + singer.getId());
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Singer> findAllWithDetails() {
        return null;
    }

    @Override
    public void insertWithDetails(Singer singer) {

    }

    @Override
    public void insertWithAlbum(Singer singer) {
        Map<String, Object> params = getSingerParams(singer);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        insertSingerOperation.updateByNamedParam(params, keyHolder);
        singer.setId(keyHolder.getKey().longValue());
        LOGGER.info("New singer inserted with id: " + singer.getId());

        List<Album> albums = singer.getAlbums();

        if (albums != null) {
            albums.forEach(album -> {
                Map<String, Object> albumParams = Map.of(
                        "singer_id", singer.getId(),
                        "title", album.getTitle(),
                        "release_date", album.getReleaseDate()
                );
                insertSingerAlbumOperation.updateByNamedParam(albumParams);
            });
            insertSingerAlbumOperation.flush();
        }

    }

    private Map<String, Object> getSingerParams(Singer singer) {
        return Map.of(
                "first_name", singer.getFirstName(),
                "last_name", singer.getLastName(),
                "birth_date", singer.getBirthDate()
        );
    }
}
