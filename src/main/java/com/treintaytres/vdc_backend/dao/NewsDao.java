package com.treintaytres.vdc_backend.dao;

import com.treintaytres.vdc_backend.Connection;
import com.treintaytres.vdc_backend.model.New;
import org.hibernate.Session;

import java.util.List;

public class NewsDao {
    public static List<New> getAllNews() {
        Session session = Connection.getSession();
        return session.createQuery("from New", New.class).getResultList();
    }
}
