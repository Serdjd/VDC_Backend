package com.treintaytres.vdc_backend.controller;

import com.treintaytres.vdc_backend.mapper.EventMapper;
import com.treintaytres.vdc_backend.mapper.StringMapper;
import com.treintaytres.vdc_backend.mapper.UserMapper;
import com.treintaytres.vdc_backend.model.Event;
import com.treintaytres.vdc_backend.model.UserWithString;
import com.treintaytres.vdc_backend.response.event.details.Details;
import com.treintaytres.vdc_backend.response.event.details.EventDetailsResponse;
import com.treintaytres.vdc_backend.response.event.details.InstrumentString;
import com.treintaytres.vdc_backend.service.EventDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/eventdetails")
public class EventDetailsController {


    private static final Logger log = LoggerFactory.getLogger(EventDetailsController.class);
    private final UserMapper userMapper;
    private final EventMapper eventMapper;
    private final StringMapper stringMapper;
    private EventDetailsService eventDetailsService;

    public EventDetailsController(EventDetailsService eventDetailsService, UserMapper userMapper, EventMapper eventMapper, StringMapper stringMapper) {
        this.eventDetailsService = eventDetailsService;
        this.userMapper = userMapper;
        this.eventMapper = eventMapper;
        this.stringMapper = stringMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDetailsResponse> getEventDetails(@PathVariable int id) {
        List<UserWithString> users = eventDetailsService.getUsers(id);
        Event event = eventDetailsService.getEvent(id);
        AtomicInteger totalAssistance = new AtomicInteger();
        if (users == null || users.isEmpty() || event == null) return ResponseEntity.badRequest().build();

        List<Details> details = new ArrayList<>();

        users.forEach(user -> {
            Details key = containsKey(details,user.getInstrumentString().getId());
            if (key != null) {
                key.getMembers().add(userMapper.toMember(user));

                if (user.getAttendance() != null)  {
                    if (user.getAttendance()) {
                        key.getInstrumentString().increaseConfirmed();
                        totalAssistance.getAndIncrement();
                    } else {
                        key.getInstrumentString().increaseCancelled();
                    }
                }
            } else {
                InstrumentString instrumentString = stringMapper.toString(user.getInstrumentString());
                details.add(
                        new Details(
                                instrumentString,
                                new ArrayList<>(Collections.singletonList(userMapper.toMember(user)))
                        )
                );
                instrumentString.setConfirmed(0);
                instrumentString.setCancelled(0);

                if (user.getAttendance() != null)  {
                    if (user.getAttendance()) {
                        instrumentString.increaseConfirmed();
                        totalAssistance.getAndIncrement();
                    } else {
                        instrumentString.increaseCancelled();
                    }
                }
            }
        });

        EventDetailsResponse eventDetailsResponse = new EventDetailsResponse();
        eventDetailsResponse.setEvent(eventMapper.toEvent(event));
        eventDetailsResponse.getEvent().setConfirmed(totalAssistance.get());
        eventDetailsResponse.setDetails(details);

        return ResponseEntity.ok(eventDetailsResponse);
    }

    private Details containsKey(List<Details> keys, int keyId) {
        for (Details details : keys) {
            if (details.getInstrumentString().getId() == keyId) return details;
        }
        return null;
    }
}
