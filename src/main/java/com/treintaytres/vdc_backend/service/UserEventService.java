package com.treintaytres.vdc_backend.service;

import com.treintaytres.vdc_backend.dao.UserEventDao;
import com.treintaytres.vdc_backend.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserEventService {

    private final UserEventDao userEventDao;

    public UserEventService(UserEventDao userEventDao) {
        this.userEventDao = userEventDao;
    }


    public List<User> getUserOfEvent(int eventId) {
        try {
            return userEventDao.getUserOfEvent(eventId);
        } catch (RuntimeException e) {
            return null;
        }
    }

    public void changeAttendance(int userId,int eventId, boolean attendance) {
        try {
            userEventDao.changeAttendance(userId,eventId,attendance);
        } catch (RuntimeException ignored) {}
    }
}
