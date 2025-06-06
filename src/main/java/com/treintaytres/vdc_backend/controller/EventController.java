package com.treintaytres.vdc_backend.controller;

import com.treintaytres.vdc_backend.mapper.EventMapper;
import com.treintaytres.vdc_backend.mapper.InstrumentStringMapper;
import com.treintaytres.vdc_backend.model.EventWithAttendance;
import com.treintaytres.vdc_backend.model.Type;
import com.treintaytres.vdc_backend.model.request.AttendanceRequest;
import com.treintaytres.vdc_backend.model.request.CreateEventRequest;
import com.treintaytres.vdc_backend.response.event.EventResponse;
import com.treintaytres.vdc_backend.response.group.CreateDataResponse;
import com.treintaytres.vdc_backend.response.group.InstrumentStringResponse;
import com.treintaytres.vdc_backend.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventMapper eventMapper;
    private final EventService eventService;
    private final InstrumentStringMapper instrumentStringMapper;

    public EventController(EventService eventService, EventMapper eventMapper, InstrumentStringMapper instrumentStringMapper) {
        this.eventService = eventService;
        this.eventMapper = eventMapper;
        this.instrumentStringMapper = instrumentStringMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<EventResponse>> getEvents(@RequestParam String filter, @PathVariable int id) {
        List<EventWithAttendance> events = switch (filter.toUpperCase()) {
            case "FUTURE" -> eventService.getFutureEvents(id);
            case "PAST" -> eventService.getPastEvents(id);
            case "CURRENT" -> eventService.getCurrentEvents(id);
            default -> eventService.getAllEvents(id);
        };
        if (events == null || events.isEmpty())  return ResponseEntity.noContent().build();

        return ResponseEntity.ok(eventMapper.toEvents(events));
    }

    @GetMapping("/create")
    public ResponseEntity<CreateDataResponse> getCreateData() {
        List<InstrumentStringResponse> result = instrumentStringMapper.toInstrumentStrings(eventService.getAllInstrumentStrings());
        List<Type> types = eventService.getAllTypes();

        if (types == null || types.isEmpty()) return ResponseEntity.noContent().build();
        if (result == null || result.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(new CreateDataResponse(result,types));
    }

    @PostMapping
    public ResponseEntity<String> addEvent(@RequestBody CreateEventRequest event) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(event.getDate(),formatter);

        LocalTime time = LocalTime.parse(event.getTime());

        Instant instant = LocalDateTime.of(date, time).atZone(ZoneId.of("Europe/Madrid")).toInstant();
        try {
            eventService.addEvent(
                    event.getType(),
                    event.getTitle(),
                    event.getComments(),
                    event.getLocation(),
                    instant,
                    event.getInstrumentStringIds()
            );
            return ResponseEntity.ok("Event added");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping
    public ResponseEntity<String> modifyEvent(@RequestBody CreateEventRequest event) {

        LocalDate date = LocalDate.parse(event.getDate());
        LocalTime time = LocalTime.parse(event.getTime());

        Instant instant = LocalDateTime.of(date, time).atZone(ZoneId.of("Europe/Madrid")).toInstant();
        try {
            eventService.modifyEvent(
                    event.getId(),
                    event.getType(),
                    event.getTitle(),
                    event.getComments(),
                    event.getLocation(),
                    instant,
                    event.getInstrumentStringIds()
            );
            return ResponseEntity.ok("Event updated");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/{user_id}/{event_id}")
    public ResponseEntity<String> updateUserAttendance(
            @PathVariable int user_id,
            @PathVariable int event_id,
            @RequestBody AttendanceRequest request
    ) {
        try {
            eventService.changeAttendance(user_id,event_id,request.getAttendance());
            return ResponseEntity.ok("success");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
