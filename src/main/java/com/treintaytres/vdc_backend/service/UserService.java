package com.treintaytres.vdc_backend.service;

import com.treintaytres.vdc_backend.dao.UserDao;
import com.treintaytres.vdc_backend.dao.UserEventDao;
import com.treintaytres.vdc_backend.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserDao userDao;
    private final UserEventDao userEventDao;

    public UserService(UserDao userDao, UserEventDao userEventDao) {
        this.userDao = userDao;
        this.userEventDao = userEventDao;
    }

    public int add(
            String email
    ) {
        try {
            return userDao.add(email);
        } catch (RuntimeException e) {
            return -1;
        }
    }

    public boolean update(
            int id,
            String username,
            int primaryInstrumentId,
            List<Integer> instrumentIds,
            String profileImagePath
    ) {
        try {
            return userDao.update(id, username, primaryInstrumentId, instrumentIds, profileImagePath);
        } catch (RuntimeException e) {
            return false;
        }
    }

    public void addUserEvents(int userId) throws Exception {
        userEventDao.addUser(userId);
    }

    public void updatePermissions(
            int id,
            int permissions
    ) {
        try {
            userDao.updatePermissions(id, permissions);
        } catch (RuntimeException ignored) {}
    }

    public void updateInstruments(
            int id,
            int primaryInstrumentId,
            List<Integer> instrumentIds
    ) {
        try {
            userDao.updateInstruments(id, primaryInstrumentId, instrumentIds);
        } catch (RuntimeException ignored) {}
    }

    public User get(int id) {
        try {
            return userDao.get(id);
        } catch (RuntimeException e) {
            return null;
        }
    }

    public User get(String email) {
        try {
            return userDao.get(email);
        } catch (RuntimeException e) {
            return null;
        }
    }

    public List<User> getAll() {
        return userDao.getAll();
    }

    public List<User> getAllNotValidate() {
        return userDao.getAllNotValidate();
    }

    public void updatePerteneceJunta(int id) {
        try{
            userDao.updatePerteneceJunta(id);
        } catch (RuntimeException ignored) {}
    }

    public void updateProfileImageUrl(
            int id,
            String profileImageUrl
    ) {
        try{
            userDao.updateProfileImageUrl(id, profileImageUrl);
        } catch (RuntimeException ignored) {}
    }

    public boolean updateValidation(
            int id,
            boolean validation
    ) {
        try {
            return userDao.updateValidation(id, validation);
        } catch (RuntimeException e) {
            return false;
        }
    }
}
