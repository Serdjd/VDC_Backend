package com.treintaytres.vdc_backend.service;

import com.treintaytres.vdc_backend.dao.EventDao;
import com.treintaytres.vdc_backend.dao.UserDao;
import com.treintaytres.vdc_backend.model.User;
import com.treintaytres.vdc_backend.model.UserStadistics;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RollCallService {
    public List<User> getUsers(int id) {
        return EventDao.getEventUsers(id);
    }
}