package com.treintaytres.vdc_backend.service;

import com.treintaytres.vdc_backend.dao.EventDao;
import com.treintaytres.vdc_backend.dao.UserDao;
import com.treintaytres.vdc_backend.model.Event;
import com.treintaytres.vdc_backend.model.User;
import com.treintaytres.vdc_backend.model.UserWithString;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventDetailsService {

    private final EventDao eventDao;

    public EventDetailsService(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    public List<UserWithString> getUsers(int id) {
        return eventDao.getEventUsersWithString(id);
    }
    public Event getEvent(int id) {
        return eventDao.getEvent(id);
    }
}