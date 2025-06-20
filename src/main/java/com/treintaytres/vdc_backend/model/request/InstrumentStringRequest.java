package com.treintaytres.vdc_backend.model.request;

public class InstrumentStringRequest {
    private String name;
    private String url;

    public InstrumentStringRequest() {}

    public InstrumentStringRequest(String name) {
        this.name = name;
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
