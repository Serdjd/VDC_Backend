package com.treintaytres.vdc_backend.response.event;

import java.time.Instant;

public class EventResponse {
    private int id;
    private int type;
    private String title;
    private Instant date;
    private String location;
    private Boolean confirmationState;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Boolean isConfirmationState() {
        return confirmationState;
    }

    public void setConfirmationState(Boolean confirmationState) {
        this.confirmationState = confirmationState;
    }
}
