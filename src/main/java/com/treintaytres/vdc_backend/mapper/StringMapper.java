package com.treintaytres.vdc_backend.mapper;

import com.treintaytres.vdc_backend.response.event.details.InstrumentString;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StringMapper {

    @Mapping(source = "id",target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "url", target = "url")
    InstrumentString toString(com.treintaytres.vdc_backend.model.InstrumentString instrumentString);
}
