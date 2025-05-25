package com.treintaytres.vdc_backend.model;

public class UserWithString {
    private User user;
    private InstrumentString instrumentString;
    private Boolean attendance;

    public UserWithString(User user, Boolean attendance) {
        this.user = user;
        this.attendance = attendance;
        this.instrumentString = user.getPrimaryInstrument().getInstrumentString();
    }

    public UserWithString() {}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public InstrumentString getInstrumentString() {
        return instrumentString;
    }

    public void setInstrumentString(InstrumentString instrumentString) {
        this.instrumentString = instrumentString;
    }

    public Boolean getAttendance() {
        return attendance;
    }

    public void setAttendance(Boolean attendance) {
        this.attendance = attendance;
    }
}
