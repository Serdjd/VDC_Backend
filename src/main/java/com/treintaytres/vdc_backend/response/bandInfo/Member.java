package com.treintaytres.vdc_backend.response.bandInfo;

import java.util.List;

public class Member {
    private Integer id;
    private String url;
    private String name;
    private String isAdmin;
    public boolean perteneceJunta;
    private boolean attendance;
    private List<Instrument> instruments;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl (String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean isPerteneceJunta() {
        return perteneceJunta;
    }

    public void setPerteneceJunta(boolean perteneceJunta) {
        this.perteneceJunta = perteneceJunta;
    }

    public boolean isAttendance() {
        return attendance;
    }

    public void setAttendance(boolean attendance) {
        this.attendance = attendance;
    }

    public List<Instrument> getInstruments() {
        return instruments;
    }

    public void setInstruments(List<Instrument> instruments) {
        this.instruments = instruments;
    }
}
