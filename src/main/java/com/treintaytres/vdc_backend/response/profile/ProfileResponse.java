package com.treintaytres.vdc_backend.response.profile;

import com.treintaytres.vdc_backend.model.UserStadistics;
import com.treintaytres.vdc_backend.response.bandInfo.Instrument;

import java.util.List;

public class ProfileResponse {
    private String url;
    private List<Instrument> instruments;
    private List<UserStadistics> stats;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Instrument> getInstruments() {
        return instruments;
    }

    public void setInstruments(List<Instrument> instruments) {
        this.instruments = instruments;
    }

    public List<UserStadistics> getStats() {
        return stats;
    }

    public void setStats(List<UserStadistics> stats) {
        this.stats = stats;
    }
}
