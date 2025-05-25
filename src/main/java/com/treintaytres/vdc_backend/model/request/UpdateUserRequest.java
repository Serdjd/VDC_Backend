package com.treintaytres.vdc_backend.model.request;

import java.util.List;

public class UpdateUserRequest {

    private String username;
    private String email;
    private int id;
    private int primaryInstrumentId;
    private List<Integer> instrumentIds;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrimaryInstrumentId() {
        return primaryInstrumentId;
    }

    public void setPrimaryInstrumentId(int primaryInstrumentId) {
        this.primaryInstrumentId = primaryInstrumentId;
    }

    public List<Integer> getInstrumentIds() {
        return instrumentIds;
    }

    public void setInstrumentIds(List<Integer> instrumentIds) {
        this.instrumentIds = instrumentIds;
    }
}
