package com.treintaytres.vdc_backend.dao;

import com.treintaytres.vdc_backend.Connection;
import com.treintaytres.vdc_backend.model.Instrument;
import com.treintaytres.vdc_backend.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.List;


public class UserDao {

    public static void add(
        String username,
        String email,
        int primaryInstrumentId,
        Instrument[] instrumentIds,
        String profileImagePath
    ) {
        Session session = Connection.getSession();
        Transaction tx = session.beginTransaction();
        try {

            List<Instrument> instruments = session.createQuery("FROM Instrument where id IN :ids",Instrument.class)
                    .setParameter("ids",instrumentIds)
                    .getResultList();

            User user = new User(
                username,
                email,
                profileImagePath,
                session.get(Instrument.class,primaryInstrumentId),
               new HashSet<>(instruments)
            );

            session.persist(user);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            System.err.println(e.getMessage());
        }
    }

    public static void updatePermissions(
            int id,
            int permissions
    ) {
        Session session = Connection.getSession();
        Transaction tx = session.beginTransaction();
        try {
            User user = session.get(User.class,id);
            user.setRol(permissions);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            System.err.println(e.getMessage());
        }
    }

    public static void updatePrimaryInstrument(
            int id,
            int primaryInstrumentId
    ) {
        Session session = Connection.getSession();
        Transaction tx = session.beginTransaction();
        try {
            User user = session.get(User.class,id);
            user.setPrimaryInstrument(session.get(Instrument.class,primaryInstrumentId));
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            System.err.println(e.getMessage());
        }
    }

    public static User get(int id) {
        Session session = Connection.getSession();
        Transaction tx = session.beginTransaction();
        try {
            User user = session.get(User.class,id);
            tx.commit();
            return user;
        } catch (Exception e) {
            tx.rollback();
            System.err.println(e.getMessage());
        }
        return null;
    }

    public static List<User> getAll() {
        Session session = Connection.getSession();
        return session.createQuery("FROM User",User.class).getResultList();
    }

    public static void updatePerteneceJunta(int id) {
        Session session = Connection.getSession();
        Transaction tx = session.beginTransaction();
        try {
            User user = session.get(User.class,id);
            user.setPerteneceJunta(!user.getPerteneceJunta());
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            System.err.println(e.getMessage());
        }
    }

    public static void updateProfileImageUrl(
            int id,
            String profileImageUrl
    ) {
        Session session = Connection.getSession();
        Transaction tx = session.beginTransaction();
        try {
            User user = session.get(User.class,id);
            user.setProfileImageUrl(profileImageUrl);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            System.err.println(e.getMessage());
        }
    }

}
