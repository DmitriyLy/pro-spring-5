package com.apress.prospring5.common;

public interface IAlbumContainer<T extends IAlbum> {
    void addAlbum(T album);
}
