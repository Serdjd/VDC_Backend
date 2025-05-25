package com.treintaytres.vdc_backend.controller;

import com.treintaytres.vdc_backend.mapper.EventMapper;
import com.treintaytres.vdc_backend.model.EventWithAttendance;
import com.treintaytres.vdc_backend.model.request.CreateEventRequest;
import com.treintaytres.vdc_backend.response.event.EventResponse;
import com.treintaytres.vdc_backend.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventMapper eventMapper;
    private EventService eventService;

    public EventController(EventService eventService, EventMapper eventMapper) {
        this.eventService = eventService;
        this.eventMapper = eventMapper;
    }

    @GetMapping("/{id}/all")
    public ResponseEntity<List<EventResponse>> getFutureEvents(@RequestParam String filter, @PathVariable int id) {
        List<EventWithAttendance> events = switch (filter) {
            case "future" -> eventService.getFutureEvents(id);
            case "past" -> eventService.getPastEvents(id);
            case "current" -> eventService.getCurrentEvents(id);
            default -> eventService.getAllEvents(id);
        };
        if (events == null || events.isEmpty())  return ResponseEntity.noContent().build();

        return ResponseEntity.ok(eventMapper.toEvents(events));
    }

    @PostMapping
    public ResponseEntity<String> addEvent(@RequestBody CreateEventRequest event) {
        eventService.addEvent(
                event.getType(),
                event.getTitle(),
                event.getComments(),
                event.getLocation(),
                event.getUserIds()
        );
        return ResponseEntity.ok("Event added");
    }

    @PutMapping
    public ResponseEntity<String> modifyEvent(@RequestBody CreateEventRequest event) {
        eventService.modifyEvent(
                event.getId(),
                event.getType(),
                event.getTitle(),
                event.getComments(),
                event.getLocation(),
                event.getUserIds()
        );
        return ResponseEntity.ok("Event updated");
    }
}
