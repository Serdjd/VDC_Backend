package com.treintaytres.vdc_backend.response.event.details;

import com.treintaytres.vdc_backend.response.bandInfo.Member;

import java.util.List;

public class Details {
    private InstrumentString instrumentString;
    private List<Member> members;

    public Details(InstrumentString instrumentString, List<Member> members) {
        this.instrumentString = instrumentString;
        this.members = members;
    }

    public InstrumentString getInstrumentString() {
        return instrumentString;
    }

    public void setInstrument(InstrumentString instrumentString) {
        this.instrumentString = instrumentString;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
