package com.treintaytres.vdc_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class UserEventId implements java.io.Serializable {
    private static final long serialVersionUID = -3132490257192337145L;
    @Column(name = "id_event", nullable = false)
    private Integer idEvent;

    @Column(name = "id_user", nullable = false)
    private Integer idUser;

    public UserEventId() {}
    public UserEventId(Integer idEvent, Integer idUser) {}

    public Integer getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Integer idEvent) {
        this.idEvent = idEvent;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserEventId entity = (UserEventId) o;
        return Objects.equals(this.idUser, entity.idUser) &&
                Objects.equals(this.idEvent, entity.idEvent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idEvent);
    }

}