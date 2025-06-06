package com.treintaytres.vdc_backend.dao;

import com.treintaytres.vdc_backend.model.Instrument;
import com.treintaytres.vdc_backend.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Repository
public class UserInstrumentDao {

    @PersistenceContext
    private EntityManager session;

}
