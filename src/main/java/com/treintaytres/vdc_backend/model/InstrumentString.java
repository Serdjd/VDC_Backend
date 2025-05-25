package com.treintaytres.vdc_backend.model;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "STRING")
public class InstrumentString {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private java.lang.String name;

    @OneToMany(mappedBy = "instrumentString")
    private Set<Instrument> instruments = new LinkedHashSet<>();

    @Lob
    @Column(name = "url")
    private java.lang.String url;

    public java.lang.String getUrl() {
        return url;
    }

    public void setUrl(java.lang.String url) {
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public Set<Instrument> getInstruments() {
        return instruments;
    }

    public void setInstruments(Set<Instrument> instruments) {
        this.instruments = instruments;
    }

}