package com.treintaytres.vdc_backend.service;

import com.treintaytres.vdc_backend.dao.UserInstrumentDao;
import com.treintaytres.vdc_backend.model.Instrument;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInstrumentService {
    public List<Instrument> update(
            int userId,
            List<Integer> newInstrumentIds
    ) {
        return UserInstrumentDao.update(userId, newInstrumentIds);
    }
}
