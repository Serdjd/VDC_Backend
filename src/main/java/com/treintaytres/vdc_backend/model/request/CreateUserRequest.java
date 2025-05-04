package com.treintaytres.vdc_backend.model.request;

import com.treintaytres.vdc_backend.model.Instrument;

public class CreateUserRequest {

    private String username;
    private String email;
    private int primaryInstrumentId;
    private Instrument[] instrumentIds;
    private byte[] profileImage;

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

    public int getPrimaryInstrumentId() {
        return primaryInstrumentId;
    }

    public void setPrimaryInstrumentId(int primaryInstrumentId) {
        this.primaryInstrumentId = primaryInstrumentId;
    }

    public Instrument[] getInstrumentIds() {
        return instrumentIds;
    }

    public void setInstrumentIds(Instrument[] instrumentIds) {
        this.instrumentIds = instrumentIds;
    }

    public byte[] getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(byte[] profileImage) {
        this.profileImage = profileImage;
    }
}
