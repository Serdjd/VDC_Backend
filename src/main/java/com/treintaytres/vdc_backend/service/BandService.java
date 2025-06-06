package com.treintaytres.vdc_backend.service;

import com.treintaytres.vdc_backend.dao.NewsDao;
import com.treintaytres.vdc_backend.dao.UserDao;
import com.treintaytres.vdc_backend.model.New;
import com.treintaytres.vdc_backend.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BandService {

    private final UserDao userDao;
    private final NewsDao newsDao;

    public BandService(UserDao userDao, NewsDao newsDao) {
        this.userDao = userDao;
        this.newsDao = newsDao;
    }

    public Boolean isAdmin(int id) {
        return userDao.isAdmin(id);
    }
    public List<User> getAllUsers() {
        return userDao.getAll();
    }
    public List<New> getAllNews() {
        return newsDao.getAllNews();
    }
}