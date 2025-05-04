package com.treintaytres.vdc_backend.service;

import com.treintaytres.vdc_backend.dao.EventDao;
import com.treintaytres.vdc_backend.model.Event;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    public List<Event> getAllEvents() {return EventDao.getAllEvents();}
    public List<Event> getFutureEvents() {
        return EventDao.getFutureEvents();
    }
    public List<Event> getPastEvents() {
        return EventDao.getPastEvents();
    }
    public List<Event> getCurrentEvents() {
        return EventDao.getCurrentEvents();
    }
    public void addEvent(
            String type,
            String title,
            String comments,
            String location,
            List<Integer> userIds
    ) {
        EventDao.addEvent(type, title, comments, location, userIds);
    }

    public void modifyEvent(
            int EventId,
            String type,
            String title,
            String comments,
            String location,
            List<Integer> userIds
    ) {
        EventDao.modifyEvent(EventId, type, title, comments, location, userIds);
    }
}