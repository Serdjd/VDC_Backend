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
    public List<UserWithString> getUsers(int id) {
        return EventDao.getEventUsersWithString(id);
    }
    public Event getEvent(int id) {
        return EventDao.getEvent(id);
    }
}