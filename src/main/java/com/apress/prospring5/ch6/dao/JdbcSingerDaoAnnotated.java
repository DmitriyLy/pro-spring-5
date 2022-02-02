package com.apress.prospring5.ch6.dao;

import com.apress.prospring5.ch6.operation.InsertSingerOperation;
import com.apress.prospring5.ch6.operation.SelectAllSingersOperation;
import com.apress.prospring5.ch6.operation.SelectSingerByFirstNameOperation;
import com.apress.prospring5.ch6.operation.UpdateSingerOperation;
import com.apress.prospring5.ch6.entities.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("singerDao")
public class JdbcSingerDaoAnnotated implements SingerDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcSingerDaoAnnotated.class);

    private DataSource dataSource;
    private SelectAllSingersOperation selectAllSingersOperation;
    private SelectSingerByFirstNameOperation selectSingerByFirstNameOperation;
    private UpdateSingerOperation updateSingerOperation;
    private InsertSingerOperation insertSingerOperation;

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
        Map<String, Object> params = Map.of(
                "first_name", singer.getFirstName(),
                "last_name", singer.getLastName(),
                "birth_date", singer.getBirthDate()
        );

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
}
