package com.treintaytres.vdc_backend.service;

import com.treintaytres.vdc_backend.dao.*;
import com.treintaytres.vdc_backend.model.EventWithAttendance;
import com.treintaytres.vdc_backend.model.InstrumentString;
import com.treintaytres.vdc_backend.model.Type;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class EventService {

    private final EventDao eventDao;
    private final InstrumentStringDao instrumentStringDao;
    private final TypeDao typeDao;
    private final UserEventDao userEventDao;

    public EventService(EventDao eventDao, InstrumentStringDao instrumentStringDao, TypeDao typeDao, UserEventDao userEventDao) {
        this.eventDao = eventDao;
        this.instrumentStringDao = instrumentStringDao;
        this.typeDao = typeDao;
        this.userEventDao = userEventDao;
    }

    public List<EventWithAttendance> getAllEvents(int id) {
        return eventDao.getAllEvents(id);
    }
    public List<EventWithAttendance> getFutureEvents(int id) {
        return eventDao.getFutureEvents(id);
    }
    public List<EventWithAttendance> getPastEvents(int id) {
        return eventDao.getPastEvents(id);
    }
    public List<EventWithAttendance> getCurrentEvents(int id) {
        return eventDao.getCurrentEvents(id);
    }
    public void addEvent(
            Integer type,
            String title,
            String comments,
            String location,
            Instant date,
            List<Integer> instrumentStringIds
    ) throws RuntimeException {
        eventDao.addEvent(type, title, comments, location, date, instrumentStringIds);
    }

    public void modifyEvent(
            int EventId,
            Integer type,
            String title,
            String comments,
            String location,
            Instant date,
            List<Integer> instrumentStringIds
    ) throws RuntimeException {
        eventDao.modifyEvent(EventId, type, title, comments, location, date, instrumentStringIds);
    }

    public List<InstrumentString> getAllInstrumentStrings() {
        return instrumentStringDao.getAllInstrumentStrings();
    }

    public List<Type> getAllTypes() {
        return typeDao.getAllNews();
    }

    public void changeAttendance(int userId,int eventId, boolean attendance) throws RuntimeException {
        userEventDao.changeAttendance(userId,eventId,attendance);
    }
}