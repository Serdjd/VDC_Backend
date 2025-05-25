package com.treintaytres.vdc_backend.service;

import com.treintaytres.vdc_backend.dao.EventDao;
import com.treintaytres.vdc_backend.dao.NewsDao;
import com.treintaytres.vdc_backend.dao.UserDao;
import com.treintaytres.vdc_backend.model.Event;
import com.treintaytres.vdc_backend.model.New;
import com.treintaytres.vdc_backend.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BandService {
    public Boolean isAdmin(int id) {
        return UserDao.isAdmin(id);
    }
    public List<User> getAllUsers() {
        return UserDao.getAll();
    }
    public List<New> getAllNews() {
        return NewsDao.getAllNews();
    }
}