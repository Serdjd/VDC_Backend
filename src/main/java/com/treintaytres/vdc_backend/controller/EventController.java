package com.treintaytres.vdc_backend.controller;

import com.treintaytres.vdc_backend.model.Event;
import com.treintaytres.vdc_backend.model.request.CreateEventRequest;
import com.treintaytres.vdc_backend.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<List<Event>> getFutureEvents(@RequestParam String filter) {
        List<Event> events = switch (filter) {
            case "future" -> eventService.getFutureEvents();
            case "past" -> eventService.getPastEvents();
            case "current" -> eventService.getCurrentEvents();
            default -> eventService.getAllEvents();
        };
        if (events == null || events.isEmpty())  return ResponseEntity.noContent().build();
        return ResponseEntity.ok(events);
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
