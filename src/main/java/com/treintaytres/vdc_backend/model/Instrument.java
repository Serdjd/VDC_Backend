package com.treintaytres.vdc_backend.model;

import jakarta.persistence.*;

import java.lang.String;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "INSTRUMENT")
public class Instrument {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "string", nullable = false, referencedColumnName = "id")
    private InstrumentString instrumentString;

    @OneToMany(mappedBy = "primaryInstrument")
    private Set<User> users_primary = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "USER_INSTRUMENT",
            joinColumns = @JoinColumn(name = "id_instrument"),
            inverseJoinColumns = @JoinColumn(name = "id_user"))
    private Set<User> users = new LinkedHashSet<>();

    @Lob
    @Column(name = "url")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InstrumentString getInstrumentString() {
        return instrumentString;
    }

    public void setInstrumentString(InstrumentString instrumentString) {
        this.instrumentString = instrumentString;
    }

    public Set<User> getUsers_primary() {
        return users_primary;
    }

    public void setUsers_primary(Set<User> users_primary) {
        this.users_primary = users_primary;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

}