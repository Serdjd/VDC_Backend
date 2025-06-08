package com.treintaytres.vdc_backend.response.bandInfo;

import java.util.List;

public class Member {
    private Integer id;
    private String email;
    private String url;
    private String name;
    private String isAdmin;
    public boolean perteneceJunta;
    private Boolean attendance;
    private List<Instrument> instruments;

    public Member() {}

    public Member(Integer id, String email) {
        this.id = id;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Boolean isAttendance() {
        return attendance;
    }

    public void setAttendance(Boolean attendance) {
        this.attendance = attendance;
    }

    public List<Instrument> getInstruments() {
        return instruments;
    }

    public void setInstruments(List<Instrument> instruments) {
        this.instruments = instruments;
    }
}
