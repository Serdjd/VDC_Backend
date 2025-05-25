package com.treintaytres.vdc_backend.response.event.details;

import java.time.Instant;

public class Event {
    private int id;
    private int type;
    private java.lang.String title;
    private java.lang.String comments;
    private Instant date;
    private java.lang.String location;
    private boolean rollCallRealized;
    private int confirmed;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public java.lang.String getTitle() {
        return title;
    }

    public void setTitle(java.lang.String title) {
        this.title = title;
    }

    public java.lang.String getComments() {
        return comments;
    }

    public void setComments(java.lang.String comments) {
        this.comments = comments;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public java.lang.String getLocation() {
        return location;
    }

    public void setLocation(java.lang.String location) {
        this.location = location;
    }

    public boolean isRollCallRealized() {
        return rollCallRealized;
    }

    public void setRollCallRealized(boolean rollCallRealized) {
        this.rollCallRealized = rollCallRealized;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }
}
