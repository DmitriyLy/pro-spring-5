package com.apress.prospring5.ch6.dao;

import com.apress.prospring5.ch6.entities.Singer;

import java.util.Collections;
import java.util.List;

public interface SingerDao {
    List<Singer> findAll();
    List<Singer> findByFirstName(String firstName);
    String findLastNameById(Long id);
    String findFirstNameById(Long id);
    void insert(Singer singer);
    void update(Singer singer);
    void delete(Long id);
    List<Singer> findAllWithDetails();
    void insertWithDetails(Singer singer);
    default String findNameById(Long id) {
        return "";
    }
    default List<Singer> findAllWithAlbums() {
        return Collections.emptyList();
    }
}
