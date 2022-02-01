package com.apress.prospring5.ch6.dao;

import com.apress.prospring5.ch6.PostgresErrorCodesTranslator;
import com.apress.prospring5.ch6.entities.Singer;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;

public class JdbcSingerDao implements SingerDao, InitializingBean {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String findNameById(Long id) {
        return jdbcTemplate.queryForObject(
                "select first_name || ' ' || last_name from singer where id =?",
                new Object[]{id}, String.class);
    }

    @Override
    public List<Singer> findAll() {
        return Collections.emptyList();
    }

    @Override
    public List<Singer> findByFirstName(String firstName) {
        return Collections.emptyList();
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

    @Override
    public void afterPropertiesSet() throws Exception {
        if (dataSource == null) {
            throw new BeanCreationException("Data source must be set");
        }
    }
}
