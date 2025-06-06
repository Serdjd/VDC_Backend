package com.treintaytres.vdc_backend.service;

import com.treintaytres.vdc_backend.dao.EventDao;
import com.treintaytres.vdc_backend.dao.UserDao;
import com.treintaytres.vdc_backend.dao.UserEventDao;
import com.treintaytres.vdc_backend.model.User;
import com.treintaytres.vdc_backend.model.UserStadistics;
import com.treintaytres.vdc_backend.model.request.RollCallRequest;
import com.treintaytres.vdc_backend.response.bandInfo.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RollCallService {

    private final EventDao eventDao;
    private final UserEventDao userEventDao;

    public RollCallService(EventDao eventDao, UserEventDao userEventDao) {
        this.eventDao = eventDao;
        this.userEventDao = userEventDao;
    }

    public List<User> getUsers(int id) {
        return eventDao.getEventUsers(id);
    }

    public void updateUsersAttendance(int id, List<RollCallRequest> requests) throws RuntimeException {
        userEventDao.rollCall(id, requests);
    }
}