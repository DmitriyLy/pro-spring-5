package com.apress.prospring5.ch8.service;

import com.apress.prospring5.ch8.entities.Singer;

import java.util.Collections;
import java.util.List;

public interface SingerService {
    List<Singer> findAll();
    List<Singer> findAllWithAlbum();
    Singer findById(Long id);
    Singer save(Singer singer);
    void delete(Singer singer);
    List<Singer> findAllByNativeQuery();
    default List<Singer> findByCriteriaQuery(String firstName, String lastName) {
        return Collections.emptyList();
    }
}
