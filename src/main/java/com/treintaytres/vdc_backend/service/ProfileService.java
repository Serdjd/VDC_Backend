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
    public User getProfile(int id) {
        return UserDao.get(id);
    }
    public List<UserStadistics> getAllUserStadistics(int id) {
        return EventDao.getUserStadistics(id);
    }
}