package com.treintaytres.vdc_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "USER_INSTRUMENT")
public class UserInstrument {
    @EmbeddedId
    private UserInstrumentId id;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    private User userId;

    @MapsId("instrumentId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_instrument", nullable = false)
    private Instrument instrumentId;

    public UserInstrument() {}
    public UserInstrument(User userId, Instrument instrumentId) {}

    public UserInstrumentId getId() {
        return id;
    }

    public void setId(UserInstrumentId id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User user) {
        this.userId = user;
    }

    public Instrument getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(Instrument instrument) {
        this.instrumentId = instrument;
    }

}