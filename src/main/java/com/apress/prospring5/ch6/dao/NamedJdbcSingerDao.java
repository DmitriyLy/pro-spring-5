package com.apress.prospring5.ch6.dao;

import com.apress.prospring5.ch6.entities.Album;
import com.apress.prospring5.ch6.entities.Singer;
import com.apress.prospring5.ch6.extractor.SingerWithDetailExtractor;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;


import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class NamedJdbcSingerDao implements SingerDao, InitializingBean {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return namedParameterJdbcTemplate;
    }

    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Singer> findAllWithAlbums() {
        String query = "select s.id, s.first_name, s.last_name, s.birth_date," +
                " a.id as album_id, a.title, a.release_date " +
                " from singer s left join album a on s.id = a.singer_id";
        return namedParameterJdbcTemplate.query(query, new SingerWithDetailExtractor());
    }

    @Override
    public String findNameById(Long id) {
        String query = "select first_name || ' ' || last_name from singer where id=:singerId";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("singerId", id);
        return namedParameterJdbcTemplate.queryForObject(query, parameters, String.class);
    }

    @Override
    public List<Singer> findAll() {
        String query = "SELECT id, first_name, last_name, birth_date from singer";
        return namedParameterJdbcTemplate.query(query, (rs, rowNum) -> {
            Singer singer = new Singer();
            singer.setId(rs.getLong("id"));
            singer.setFirstName(rs.getString("first_name"));
            singer.setLastName(rs.getString("last_name"));
            singer.setBirthDate(rs.getDate("birth_date"));
            return singer;
        });
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
        if (namedParameterJdbcTemplate == null) {
            throw new BeanCreationException("namedParameterJdbcTemplate source must be set");
        }
    }

    private static final class SingerMapper implements RowMapper<Singer> {

        @Override
        public Singer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Singer singer = new Singer();
            singer.setId(rs.getLong("id"));
            singer.setFirstName(rs.getString("first_name"));
            singer.setLastName(rs.getString("last_name"));
            singer.setBirthDate(rs.getDate("birth_date"));
            return singer;
        }
    }
}
