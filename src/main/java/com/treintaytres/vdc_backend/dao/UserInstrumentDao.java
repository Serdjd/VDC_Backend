package com.treintaytres.vdc_backend.dao;

import com.treintaytres.vdc_backend.Connection;
import com.treintaytres.vdc_backend.model.Instrument;
import com.treintaytres.vdc_backend.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.List;

public class UserInstrumentDao {

    public static List<Instrument> update(
            int userId,
            List<Integer> newInstrumentIds
    ) {
        Session session = Connection.getSession();
        Transaction tx = session.beginTransaction();
        try {
            User user = session.get(User.class,userId);

            List<Instrument> instruments = session.createQuery("FROM Instrument where id IN :ids", Instrument.class)
                    .setParameter("ids",newInstrumentIds)
                    .getResultList();

            user.setInstruments(new HashSet<>(instruments));
            tx.commit();

            return instruments;
        } catch (Exception e) {
            tx.rollback();
            System.err.println(e.getMessage());
            return null;
        }
    }
}
