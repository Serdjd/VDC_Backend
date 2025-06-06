package com.treintaytres.vdc_backend.service;

import com.treintaytres.vdc_backend.dao.UserEventDao;
import com.treintaytres.vdc_backend.dao.UserInstrumentDao;
import com.treintaytres.vdc_backend.model.Instrument;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInstrumentService {

    private final UserInstrumentDao userInstrumentDao;

    public UserInstrumentService(UserInstrumentDao userInstrumentDao) {
        this.userInstrumentDao = userInstrumentDao;
    }

}
