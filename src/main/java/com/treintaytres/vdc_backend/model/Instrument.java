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
    @JoinColumn(name = "string", nullable = false, referencedColumnName = "name")
    private com.treintaytres.vdc_backend.model.String string;

    @OneToMany(mappedBy = "primaryInstrument")
    private Set<User> users_primary = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "USER_INSTRUMENT",
            joinColumns = @JoinColumn(name = "instrument_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users = new LinkedHashSet<>();

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

    public com.treintaytres.vdc_backend.model.String getString() {
        return string;
    }

    public void setString(com.treintaytres.vdc_backend.model.String string) {
        this.string = string;
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