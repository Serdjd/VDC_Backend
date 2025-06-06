package com.treintaytres.vdc_backend.dao;

import com.treintaytres.vdc_backend.model.InstrumentString;
import com.treintaytres.vdc_backend.model.request.InstrumentStringRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class InstrumentStringDao {

    @PersistenceContext
    private EntityManager session;


    public List<InstrumentString> getAllInstrumentStrings() {
        return session.createQuery("from InstrumentString",InstrumentString.class).getResultList();
    }

    @Transactional
    public boolean insertInstrumentString(InstrumentStringRequest request) throws RuntimeException {
        try {
            InstrumentString string = new InstrumentString();
            string.setName(request.getName());
            string.setUrl(request.getUrl());

            session.persist(string);

            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public boolean addImage(int id, String url) throws RuntimeException {
        try {
            InstrumentString string = session.find(InstrumentString.class,id);
            string.setUrl(url);
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String getName(int id) {
        return session.find(InstrumentString.class,id).getName();
    }

}
