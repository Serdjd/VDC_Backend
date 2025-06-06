package com.treintaytres.vdc_backend.dao;

import com.treintaytres.vdc_backend.model.Type;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TypeDao {

    @PersistenceContext
    private EntityManager session;

    public List<Type> getAllNews() {
        return session.createQuery("from Type", Type.class).getResultList();
    }
}
