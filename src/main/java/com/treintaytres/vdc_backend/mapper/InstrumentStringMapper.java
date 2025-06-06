package com.treintaytres.vdc_backend.mapper;

import com.treintaytres.vdc_backend.model.InstrumentString;
import com.treintaytres.vdc_backend.response.group.InstrumentStringResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InstrumentStringMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "url", target = "url")
    InstrumentStringResponse toInstrumentString(InstrumentString instrumentString);

    List<InstrumentStringResponse> toInstrumentStrings(List<InstrumentString> instrumentStrings);
}
