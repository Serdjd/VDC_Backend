package com.treintaytres.vdc_backend.model.request;

import java.util.List;

public class CreateEventRequest {

    private int id;
    private Integer type;
    private String title;
    private String comments;
    private String location;
    private String date;
    private String time;
    private List<Integer> instrumentStringIds;

    public CreateEventRequest() {}

    public CreateEventRequest(Integer type, String title, String comments, String location, String date, String time, List<Integer> instrumentStringIds) {
        this.type = type;
        this.title = title;
        this.comments = comments;
        this.location = location;
        this.date = date;
        this.time = time;
        this.instrumentStringIds = instrumentStringIds;
    }

    public CreateEventRequest(int id,Integer type, String title, String comments, String location, String date, String time, List<Integer> instrumentStringIds) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.comments = comments;
        this.location = location;
        this.date = date;
        this.time = time;
        this.instrumentStringIds = instrumentStringIds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<Integer> getInstrumentStringIds() {
        return instrumentStringIds;
    }

    public void setInstrumentStringIds(List<Integer> instrumentStringIds) {
        this.instrumentStringIds = instrumentStringIds;
    }
}
