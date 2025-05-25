package com.treintaytres.vdc_backend.mapper;

import com.treintaytres.vdc_backend.model.EventWithAttendance;
import com.treintaytres.vdc_backend.response.event.EventResponse;
import com.treintaytres.vdc_backend.response.event.details.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(source = "event.id", target = "id")
    @Mapping(source = "event.type", target = "type")
    @Mapping(source = "event.title", target = "title")
    @Mapping(source = "event.date", target = "date")
    @Mapping(source = "event.location", target = "location")
    @Mapping(source = "withAttend", target = "confirmationState")
    EventResponse toEvent(EventWithAttendance eventWithAttendance);

    List<EventResponse> toEvents(List<EventWithAttendance> eventWithAttendance);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "comments", target = "comments")
    @Mapping(source = "date", target = "date")
    @Mapping(source = "location", target = "location")
    @Mapping(source = "rollCallMaked", target = "rollCallRealized")
    Event toEvent(com.treintaytres.vdc_backend.model.Event event);
}
