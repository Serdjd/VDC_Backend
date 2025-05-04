package com.treintaytres.vdc_backend.service;

import com.treintaytres.vdc_backend.dao.UserEventDao;
import com.treintaytres.vdc_backend.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserEventService {

    public void rollCall(int eventId, Map<Integer,Boolean> attendance) {
        UserEventDao.rollCall(eventId, attendance);
    }

    public List<User> getUserOfEvent(int eventId) {
        return UserEventDao.getUserOfEvent(eventId);
    }

    public void changeAttendance(int userId,int eventId, boolean attendance) {
        UserEventDao.changeAttendance(userId, eventId, attendance);
    }
}
