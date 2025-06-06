package com.treintaytres.vdc_backend.model.request;

public class EmailUserRequest {
    private String email;

    public EmailUserRequest() {}
    public EmailUserRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
