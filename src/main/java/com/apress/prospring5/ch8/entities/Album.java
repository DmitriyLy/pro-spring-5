package com.apress.prospring5.ch8.entities;

import com.apress.prospring5.common.IAlbum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "album")
public class Album implements Serializable, IAlbum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Version
    @Column(name = "version")
    private int version;

    @Column(name = "title")
    private String title;

    @Temporal(TemporalType.DATE)
    @Column(name = "release_date")
    private Date releaseDate;

    @ManyToOne
    @JoinColumn(name = "singer_id")
    private Singer singer;

    public Album() {
    }

    public Album(String title, Date releaseDate) {
        this.title = title;
        this.releaseDate = releaseDate;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int getVersion() {
        return version;
    }

    @Override
    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Date getReleaseDate() {
        return releaseDate;
    }

    @Override
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public Singer getSinger() {
        return singer;
    }

    @Override
    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
