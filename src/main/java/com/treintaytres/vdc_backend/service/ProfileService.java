package com.treintaytres.vdc_backend.service;

import com.treintaytres.vdc_backend.dao.EventDao;
import com.treintaytres.vdc_backend.dao.UserDao;
import com.treintaytres.vdc_backend.model.User;
import com.treintaytres.vdc_backend.model.UserStadistics;
import com.treintaytres.vdc_backend.response.bandInfo.Instrument;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    private final UserDao userDao;
    private final EventDao eventDao;

    public ProfileService(
            UserDao userDao,
            EventDao eventDao
    ) {
        this.userDao = userDao;
        this.eventDao = eventDao;
    }

    public User getProfile(int id) {
        try {
            return userDao.get(id);
        } catch (Exception e) {
            return null;
        }
    }
    public List<UserStadistics> getAllUserStadistics(int id) {
        return eventDao.getUserStadistics(id);
    }
}