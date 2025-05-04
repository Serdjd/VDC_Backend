package com.treintaytres.vdc_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class UserInstrumentId implements java.io.Serializable {
    private static final long serialVersionUID = 8972530611330861148L;
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "instrument_id", nullable = false)
    private Integer instrumentId;

    public UserInstrumentId() {}
    public UserInstrumentId(Integer userId, Integer instrumentId) {
        this.userId = userId;
        this.instrumentId = instrumentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(Integer instrumentId) {
        this.instrumentId = instrumentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserInstrumentId entity = (UserInstrumentId) o;
        return Objects.equals(this.instrumentId, entity.instrumentId) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instrumentId, userId);
    }

}