package com.treintaytres.vdc_backend.model;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.lang.String;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "EVENT")
public class Event {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "type", nullable = false)
    private Integer type;

    @Lob
    @Column(name = "title", nullable = false)
    private String title;

    @Lob
    @Column(name = "comments")
    private String comments;

    @ColumnDefault("current_timestamp()")
    @Column(name = "date", nullable = false)
    private Instant date;

    @Lob
    @Column(name = "location", nullable = false)
    private String location;

    @OneToMany(mappedBy = "idEvent")
    private Set<UserEvent> userEvents = new LinkedHashSet<>();

    @ColumnDefault("0")
    @Column(name = "roll_call_maked", nullable = false)
    private Boolean rollCallMaked = false;

    public Boolean getRollCallMaked() {
        return rollCallMaked;
    }

    public void setRollCallMaked(Boolean rollCallMaked) {
        this.rollCallMaked = rollCallMaked;
    }


    public Event() {}
    public Event(Integer type, String title, String comments, String location) {
        this.type = type;
        this.title = title;
        this.comments = comments;
        this.location = location;
    }

    @PostUpdate
    public void postUpdate() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<UserEvent> getUserEvents() {
        return userEvents;
    }

    public void setUserEvents(Set<UserEvent> userEvents) {
        this.userEvents = userEvents;
    }

}