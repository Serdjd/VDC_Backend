package com.treintaytres.vdc_backend.model.request;

import java.util.List;

public class InstrumentsUserRquest {
    private int primaryInstrumentId;
    private List<Integer> instrumentIds;

    public InstrumentsUserRquest() {}

    public InstrumentsUserRquest(int primaryInstrumentId, List<Integer> instrumentIds) {
        this.primaryInstrumentId = primaryInstrumentId;
        this.instrumentIds = instrumentIds;

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
