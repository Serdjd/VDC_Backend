package com.treintaytres.vdc_backend.service;

import com.treintaytres.vdc_backend.dao.EventDao;
import com.treintaytres.vdc_backend.model.EventWithAttendance;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    public List<EventWithAttendance> getAllEvents(int id) {
        return EventDao.getAllEvents(id);
    }
    public List<EventWithAttendance> getFutureEvents(int id) {
        return EventDao.getFutureEvents(id);
    }
    public List<EventWithAttendance> getPastEvents(int id) {
        return EventDao.getPastEvents(id);
    }
    public List<EventWithAttendance> getCurrentEvents(int id) {
        return EventDao.getCurrentEvents(id);
    }
    public void addEvent(
            Integer type,
            String title,
            String comments,
            String location,
            List<Integer> userIds
    ) {
        EventDao.addEvent(type, title, comments, location, userIds);
    }

    public void modifyEvent(
            int EventId,
            Integer type,
            String title,
            String comments,
            String location,
            List<Integer> userIds
    ) {
        EventDao.modifyEvent(EventId, type, title, comments, location, userIds);
    }
}