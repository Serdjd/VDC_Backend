package com.treintaytres.vdc_backend.service;

import com.treintaytres.vdc_backend.dao.UserDao;
import com.treintaytres.vdc_backend.model.Instrument;
import com.treintaytres.vdc_backend.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    public void add(
            String username,
            String email,
            int primaryInstrumentId,
            Instrument[] instrumentIds,
            String profileImagePath
    ) {
        UserDao.add(username, email, primaryInstrumentId, instrumentIds, profileImagePath);
    }

    public void updatePermissions(
            int id,
            int permissions
    ) {
        UserDao.updatePermissions(id, permissions);
    }

    public void updatePrimaryInstrument(
            int id,
            int primaryInstrumentId
    ) {
        UserDao.updatePrimaryInstrument(id, primaryInstrumentId);
    }

    public User get(int id) {
        return UserDao.get(id);
    }

    public List<User> getAll() {
        return UserDao.getAll();
    }

    public void updatePerteneceJunta(int id) {
        UserDao.updatePerteneceJunta(id);
    }

    public void updateProfileImageUrl(
            int id,
            String profileImageUrl
    ) {
        UserDao.updateProfileImageUrl(id, profileImageUrl);
    }
}
