package com.apress.prospring5.common;

import com.apress.prospring5.ch8.entities.Singer;

import java.util.Date;

public interface IAlbum {
    Long getId();

    void setId(Long id);

    int getVersion();

    void setVersion(int version);

    String getTitle();

    void setTitle(String title);

    Date getReleaseDate();

    void setReleaseDate(Date releaseDate);

    Singer getSinger();

    void setSinger(Singer singer);
}
