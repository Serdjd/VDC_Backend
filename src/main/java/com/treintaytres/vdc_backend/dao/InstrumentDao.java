package com.treintaytres.vdc_backend.dao;

import com.treintaytres.vdc_backend.model.Instrument;
import com.treintaytres.vdc_backend.model.InstrumentString;
import com.treintaytres.vdc_backend.model.request.InstrumentRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class InstrumentDao {

    @PersistenceContext
    private EntityManager session;

    public List<Instrument> getAllInstruments() {
        return session.createQuery("from Instrument", Instrument.class).getResultList();
    }

    @Transactional
    public boolean insertInstrument(InstrumentRequest request) throws RuntimeException {
        try {
            InstrumentString string = session.find(InstrumentString.class,request.getString());
            Instrument instrument = new Instrument();
            instrument.setInstrumentString(string);
            instrument.setName(request.getName());
            instrument.setUrl(request.getUrl());

            session.persist(instrument);

            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public boolean addImage(int id, String url) throws RuntimeException {
        try {
            Instrument instrument = session.find(Instrument.class,id);
            instrument.setUrl(url);
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String getName(int id) {
        return session.find(Instrument.class,id).getName();
    }
}
