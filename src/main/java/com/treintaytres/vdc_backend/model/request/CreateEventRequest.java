package com.treintaytres.vdc_backend.model.request;

import java.util.List;

public class CreateEventRequest {

    private int id;
    private Integer type;
    private String title;
    private String comments;
    private String location;
    private List<Integer> userIds;

    public CreateEventRequest() {}

    public CreateEventRequest(Integer type, String title, String comments, String location, List<Integer> userIds) {
        this.type = type;
        this.title = title;
        this.comments = comments;
        this.location = location;
        this.userIds = userIds;
    }

    public CreateEventRequest(int id,Integer type, String title, String comments, String location, List<Integer> userIds) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.comments = comments;
        this.location = location;
        this.userIds = userIds;
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

    public List<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Integer> userIds) {
        this.userIds = userIds;
    }
}
