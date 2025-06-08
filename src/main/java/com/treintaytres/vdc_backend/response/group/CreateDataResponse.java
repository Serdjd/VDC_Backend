package com.treintaytres.vdc_backend.response.group;

import com.treintaytres.vdc_backend.model.Type;

import java.util.List;

public class CreateDataResponse {
    private List<InstrumentStringResponse> groups;
    private List<Type> types;

    public CreateDataResponse(List<InstrumentStringResponse> groups, List<Type> types) {
        this.groups = groups;
        this.types = types;
    }

    public List<InstrumentStringResponse> getGroups() {
        return groups;
    }

    public void setGroups(List<InstrumentStringResponse> groups) {
        this.groups = groups;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }
}
