package com.treintaytres.vdc_backend.service;

import com.treintaytres.vdc_backend.dao.InstrumentDao;
import com.treintaytres.vdc_backend.model.Instrument;
import com.treintaytres.vdc_backend.model.request.InstrumentRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstrumentService {

    private final InstrumentDao instrumentDao;

    public InstrumentService(InstrumentDao instrumentDao) {
        this.instrumentDao = instrumentDao;
    }

    public List<Instrument> getAllInstruments() {
        return instrumentDao.getAllInstruments();
    }

    public boolean addInstrument(InstrumentRequest instrument) {
        try {
            return instrumentDao.insertInstrument(instrument);
        } catch (RuntimeException e) {
            return false;
        }
    }

    public boolean addImage(int id, String url) {
        try {
            return instrumentDao.addImage(id,url);
        } catch (RuntimeException e) {
            return false;
        }
    }

    public String getName(int id) {
        return instrumentDao.getName(id);
    }
}
