package com.treintaytres.vdc_backend.model;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.lang.String;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "username", length = 100)
    private String username;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Lob
    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @ColumnDefault("0")
    @Column(name = "rol")
    private Integer rol;

    @ColumnDefault("0")
    @Column(name = "pertenece_junta")
    private Boolean perteneceJunta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "primary_instrument")
    private Instrument primaryInstrument;

    @OneToMany(mappedBy = "idUser")
    private Set<com.treintaytres.vdc_backend.model.UserEvent> userEvents = new LinkedHashSet<>();

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "USER_INSTRUMENT",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_instrument"))
    private Set<Instrument> instruments = new LinkedHashSet<>();

    @Column(name = "validado", nullable = false)
    private Boolean validado = false;

    public Boolean getValidado() {
        return validado;
    }

    public void setValidado(Boolean validado) {
        this.validado = validado;
    }


    public User() {}

    public User(
            String username,
            String email,
            String profileImageUrl,
            Instrument primaryInstrument,
            Set<Instrument> instruments
    ) {
        this.username = username;
        this.email = email;
        this.profileImageUrl = profileImageUrl;
        this.primaryInstrument = primaryInstrument;
        this.instruments = instruments;
        this.rol = 0;
        this.perteneceJunta = false;
        this.validado = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public Integer getRol() {
        return rol;
    }

    public void setRol(Integer rol) {
        this.rol = rol;
    }

    public Boolean getPerteneceJunta() {
        return perteneceJunta;
    }

    public void setPerteneceJunta(Boolean perteneceJunta) {
        this.perteneceJunta = perteneceJunta;
    }

    public Instrument getPrimaryInstrument() {
        return primaryInstrument;
    }

    public void setPrimaryInstrument(Instrument primaryInstrument) {
        this.primaryInstrument = primaryInstrument;
    }

    public Set<com.treintaytres.vdc_backend.model.UserEvent> getUserEvents() {
        return userEvents;
    }

    public void setUserEvents(Set<com.treintaytres.vdc_backend.model.UserEvent> userEvents) {
        this.userEvents = userEvents;
    }

    public Set<Instrument> getInstruments() {
        return instruments;
    }

    public void setInstruments(Set<Instrument> instruments) {
        this.instruments = instruments;
    }

}