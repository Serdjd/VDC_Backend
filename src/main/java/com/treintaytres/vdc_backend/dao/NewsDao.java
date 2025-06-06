package com.treintaytres.vdc_backend.dao;

import com.treintaytres.vdc_backend.model.New;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NewsDao {

    @PersistenceContext
    private EntityManager session;

    public List<New> getAllNews() {
        return session.createQuery("from New", New.class).getResultList();
    }
}
