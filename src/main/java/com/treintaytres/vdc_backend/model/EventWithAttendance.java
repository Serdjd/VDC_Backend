package com.treintaytres.vdc_backend.model;

public class EventWithAttendance {

    private Event event;
    private Boolean withAttend;

    public EventWithAttendance() {}

    public EventWithAttendance(Event event, Boolean withAttend) {
        this.event = event;
        this.withAttend = withAttend;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Boolean getWithAttend() {
        return withAttend;
    }

    public void setWithAttend(Boolean withAttend) {
        this.withAttend = withAttend;
    }
}
