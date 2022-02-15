package com.apress.prospring5.ch7.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "instrument")
public class Instrument {
    private String instrumentId;
    private Set<Singer> singers;

    @Id
    @Column(name = "instrument_id")
    public String getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(String instrumentId) {
        this.instrumentId = instrumentId;
    }

    @ManyToMany
    @JoinTable(name = "singer_instrument",
            joinColumns = @JoinColumn(name = "instrument_id"),
            inverseJoinColumns = @JoinColumn(name = "singer_id"))
    public Set<Singer> getSingers() {
        return singers;
    }

    public void setSingers(Set<Singer> singers) {
        this.singers = singers;
    }

    public void addSinger(Singer singer) {
        if (singers == null) {
            singers = new HashSet<>();
        }
        singers.add(singer);
    }

    @Override
    public String toString() {
        return "Instrument{" +
                "instrumentId='" + instrumentId + '\'' +
                '}';
    }
}
