package com.treintaytres.vdc_backend;

import com.treintaytres.vdc_backend.model.*;
import com.treintaytres.vdc_backend.model.InstrumentString;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Connection {
    private static Configuration configuration = null;
    private static SessionFactory sessionFactory = null;
    private static Session session = null;
    private static Session sessionIntercepted = null;

    private static void init() {
        if (configuration == null) {
            configuration = new Configuration().configure();
            configuration.addAnnotatedClass(Event.class);
            configuration.addAnnotatedClass(Instrument.class);
            configuration.addAnnotatedClass(InstrumentString.class);
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(UserEvent.class);
            configuration.addAnnotatedClass(UserEventId.class);
            configuration.addAnnotatedClass(UserInstrument.class);
            configuration.addAnnotatedClass(UserInstrumentId.class);
            configuration.addAnnotatedClass(New.class);
            sessionFactory = configuration.buildSessionFactory();
        }
    }

    public static Session getSession() {
        if (session == null || !session.isOpen()) {
            init();
            session = sessionFactory.openSession();
        }
        return session;
    }

    public static Session getSessionIntercepted() {
        if (sessionIntercepted == null || !sessionIntercepted.isOpen()) {
            init();
            sessionIntercepted = sessionFactory.withOptions().interceptor(new EventChangeInterceptor()).openSession();
        }
        return sessionIntercepted;
    }

    public static Transaction getTransaction() {
        return getSession().beginTransaction();
    }
    public static void close() {
        session.close();
        sessionFactory.close();
    }
}
