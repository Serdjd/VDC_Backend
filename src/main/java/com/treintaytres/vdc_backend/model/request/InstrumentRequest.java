package com.treintaytres.vdc_backend.model.request;

public class InstrumentRequest {
    private String name;
    private String url;
    private int string;

    public InstrumentRequest() {}

    public InstrumentRequest(String name, int string) {
        this.name = name;
        this.string = string;
    }

    public InstrumentRequest(String name) {
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

    public int getString() {
        return string;
    }

    public void setString(int string) {
        this.string = string;
    }
}
