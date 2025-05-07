package com.treintaytres.vdc_backend.model;

import jakarta.persistence.*;

import java.lang.String;

@Entity
@Table(name = "USER_EVENT")
public class UserEvent {
    @EmbeddedId
    private UserEventId id;

    @MapsId("idEvent")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_event", nullable = false)
    private Event idEvent;

    @MapsId("idUser")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    private User idUser;

    @Column(name = "will_attend")
    private Boolean willAttend;

    @Column(name = "attended")
    private Boolean attended;

    public UserEvent() {}
    public UserEvent(UserEventId id) {
        this.id = id;
    }

    public UserEventId getId() {
        return id;
    }

    public void setId(UserEventId id) {
        this.id = id;
    }

    public Event getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Event idEvent) {
        this.idEvent = idEvent;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public Boolean getWillAttend() {
        return willAttend;
    }

    public void setWillAttend(Boolean willAttend) {
        this.willAttend = willAttend;
    }

    public Boolean getAttended() {
        return attended;
    }

    public void setAttended(Boolean attended) {
        this.attended = attended;
    }

}