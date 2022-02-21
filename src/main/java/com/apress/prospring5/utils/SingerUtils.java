package com.apress.prospring5.utils;

import com.apress.prospring5.common.IAlbum;
import com.apress.prospring5.common.IAlbumContainer;

import java.sql.Date;

public class SingerUtils {

    public static <A extends IAlbum> void addAlbum(IAlbumContainer container, A album, String title, Date releaseDate) {
        album.setTitle(title);
        album.setReleaseDate(releaseDate);
        container.addAlbum(album);
    }
}
