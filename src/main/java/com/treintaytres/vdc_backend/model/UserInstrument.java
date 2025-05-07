package com.treintaytres.vdc_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "USER_INSTRUMENT")
public class UserInstrument {
    @EmbeddedId
    private UserInstrumentId id;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @MapsId("instrumentId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "instrument_id", nullable = false)
    private Instrument instrument;

    public UserInstrument() {}
    public UserInstrument(User user, Instrument instrument) {}

    public UserInstrumentId getId() {
        return id;
    }

    public void setId(UserInstrumentId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

}