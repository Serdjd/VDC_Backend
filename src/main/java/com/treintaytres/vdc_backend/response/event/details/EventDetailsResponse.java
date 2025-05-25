package com.treintaytres.vdc_backend.response.event.details;

import java.util.List;

public class EventDetailsResponse {
    private Event event;
    private List<Details> details;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public List<Details> getDetails() {
        return details;
    }

    public void setDetails(List<Details> details) {
        this.details = details;
    }
}
