package com.apress.prospring5.ch6.dao;

import com.apress.prospring5.ch6.SelectAllSingers;
import com.apress.prospring5.ch6.SelectSingerByFirstName;
import com.apress.prospring5.ch6.entities.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("singerDao")
public class JdbcSingerDaoAnnotated implements SingerDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcSingerDaoAnnotated.class);

    private DataSource dataSource;
    private SelectAllSingers selectAllSingers;
    private SelectSingerByFirstName selectSingerByFirstName;

    public SelectSingerByFirstName getSelectSingerByFirstName() {
        return selectSingerByFirstName;
    }

    @Autowired
    public void setSelectSingerByFirstName(SelectSingerByFirstName selectSingerByFirstName) {
        this.selectSingerByFirstName = selectSingerByFirstName;
    }

    public SelectAllSingers getSelectAllSingers() {
        return selectAllSingers;
    }

    @Resource(name = "selectAllSingers")
    public void setSelectAllSingers(SelectAllSingers selectAllSingers) {
        this.selectAllSingers = selectAllSingers;
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
        return selectAllSingers.execute();
    }

    @Override
    public List<Singer> findByFirstName(String firstName) {
        Map<String, Object> params = new HashMap<>();
        params.put("first_name", firstName);
        return selectSingerByFirstName.executeByNamedParam(params);
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

    }

    @Override
    public void update(Singer singer) {

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
