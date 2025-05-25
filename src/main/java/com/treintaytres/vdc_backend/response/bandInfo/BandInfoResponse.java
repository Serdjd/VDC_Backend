package com.treintaytres.vdc_backend.response.bandInfo;

import com.treintaytres.vdc_backend.model.New;

import java.util.List;

public class BandInfoResponse {
    private boolean isAdmin;
    private List<New> news;
    private List<Member> members;

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public List<New> getNews() {
        return news;
    }

    public void setNews(List<New> news) {
        this.news = news;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
