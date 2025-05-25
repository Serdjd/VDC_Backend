package com.treintaytres.vdc_backend.response.event.details;

public class InstrumentString {
    private int id;
    private String name;
    private String url;
    private int confirmed;
    private int cancelled;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public int getCancelled() {
        return cancelled;
    }

    public void setCancelled(int cancelled) {
        this.cancelled = cancelled;
    }

    public void increaseConfirmed() {
        this.confirmed++;
    }

    public void increaseCancelled() {
        this.cancelled++;
    }
}
