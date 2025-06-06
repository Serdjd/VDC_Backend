package com.treintaytres.vdc_backend.model.request;

public class RollCallRequest {

    private Integer id;
    private Boolean attendance;

    public RollCallRequest(Integer id, Boolean attendance) {
        this.id = id;
        this.attendance = attendance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getAttendance() {
        return attendance;
    }

    public void setAttendance(Boolean attendance) {
        this.attendance = attendance;
    }
}
