package com.treintaytres.vdc_backend.model.request;

import java.util.List;

public class UpdateUserRequest {

    private String username;
    private int primaryInstrumentId;
    private List<Integer> instrumentIds;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
