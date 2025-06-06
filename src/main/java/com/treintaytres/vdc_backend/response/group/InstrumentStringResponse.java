package com.treintaytres.vdc_backend.response.group;

public class InstrumentStringResponse {
    private int id;
    private String name;
    private String url;

    public InstrumentStringResponse() {}

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
}
