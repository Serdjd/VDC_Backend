package com.treintaytres.vdc_backend.response.instrument;

import com.treintaytres.vdc_backend.response.bandInfo.Instrument;

import java.util.List;

public class InstrumentsResponse {

    private List<Instrument> instruments;

    public InstrumentsResponse(List<Instrument> instruments) {
        this.instruments = instruments;
    }

    public List<Instrument> getInstruments() {
        return instruments;
    }

    public void setInstruments(List<Instrument> instruments) {
        this.instruments = instruments;
    }
}
