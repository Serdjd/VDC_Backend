package com.treintaytres.vdc_backend.dao;

import com.treintaytres.vdc_backend.model.Instrument;
import com.treintaytres.vdc_backend.model.User;
import com.treintaytres.vdc_backend.utils.Constants;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Repository
public class UserDao {

    @PersistenceContext
    private EntityManager session;

    @Transactional
    public int add(
        String email
    ) throws RuntimeException {
        try {
            User user = new User();
            user.setEmail(email);
            session.persist(user);
            int id = user.getId();
            return id;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
    @Transactional
    public boolean update(
            int id,
            String username,
            int primaryInstrumentId,
            List<Integer> instrumentIds,
            String profileImagePath
    ) throws RuntimeException {
        try {
            List<Instrument> instruments = session.createQuery("FROM Instrument where id IN :ids",Instrument.class)
                    .setParameter("ids",instrumentIds)
                    .getResultList();

            User user = session.find(User.class,id);
            user.setUsername(username);
            user.setProfileImageUrl(profileImagePath);
            user.setRol(0);
            user.setPerteneceJunta(false);
            user.setPrimaryInstrument(session.find(Instrument.class,primaryInstrumentId));
            user.setInstruments(new HashSet<>(instruments));
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public void updatePermissions(
            int id,
            int permissions
    ) throws RuntimeException {
        try {
            User user = session.find(User.class,id);
            user.setRol(permissions);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public void updateInstruments(
            int id,
            int primaryInstrumentId,
            List<Integer> instrumentIds
    ) throws RuntimeException {
        try {
            User user = session.find(User.class,id);
            user.setPrimaryInstrument(session.find(Instrument.class,primaryInstrumentId));
            List<Instrument> instruments = new ArrayList<>();
            instrumentIds.forEach(instrumentId -> {
                instruments.add(session.find(Instrument.class,instrumentId));
            });
            user.setInstruments(new HashSet<>(instruments));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
    @Transactional
    public User get(int id) throws RuntimeException {
        try {
            User user = session.find(User.class,id);
            return user;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public User get(String email) throws RuntimeException {
        try {
            User user = session
                    .createQuery("from User where email = :email",User.class)
                    .setParameter("email",email)
                    .getSingleResult();
            return user;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public Boolean isAdmin(int id) {
        User user = session.find(User.class,id);
        if (user == null) return null;
        return user.getRol() == Constants.ADMIN;
    }

    public List<User> getAll() {
        return session.createQuery("FROM User where profileImageUrl is not null and primaryInstrument is not null and validado = true ",User.class).getResultList();
    }

    @Transactional
    public void updatePerteneceJunta(int id) throws RuntimeException {
        try {
            User user = session.find(User.class,id);
            user.setPerteneceJunta(!user.getPerteneceJunta());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public void updateProfileImageUrl(
            int id,
            String profileImageUrl
    ) throws RuntimeException {
        try {
            User user = session.find(User.class,id);
            user.setProfileImageUrl(profileImageUrl);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public boolean updateValidation(
            int id,
            boolean validation
    ) throws RuntimeException {
        try {
            User user = session.find(User.class,id);
            user.setValidado(validation);
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
