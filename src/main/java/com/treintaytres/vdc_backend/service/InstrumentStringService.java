package com.treintaytres.vdc_backend.service;

import com.treintaytres.vdc_backend.dao.InstrumentStringDao;
import com.treintaytres.vdc_backend.model.request.InstrumentStringRequest;
import org.springframework.stereotype.Service;

@Service
public class InstrumentStringService {

    private final InstrumentStringDao instrumentStringDao;

    public InstrumentStringService(InstrumentStringDao instrumentStringDao) {
        this.instrumentStringDao = instrumentStringDao;
    }

    public boolean addInstrumentString(InstrumentStringRequest instrumentString) {
        try {
            return instrumentStringDao.insertInstrumentString(instrumentString);
        } catch (RuntimeException e) {
            return false;
        }
    }

    public boolean addImage(int id, String url) {
        try {
            return instrumentStringDao.addImage(id,url);
        } catch (RuntimeException e) {
            return false;
        }
    }

    public String getName(int id) {
        return instrumentStringDao.getName(id);
    }

}
