package com.treintaytres.vdc_backend.dao;

import com.treintaytres.vdc_backend.Connection;
import com.treintaytres.vdc_backend.model.Event;
import com.treintaytres.vdc_backend.model.UserEvent;
import com.treintaytres.vdc_backend.model.UserEventId;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EventDao {

    public static List<Event> getAllEvents() {
        Session session = Connection.getSession();
        return session.createQuery("from Event", Event.class).getResultList();
    }

    public static List<Event> getFutureEvents() {
        Session session = Connection.getSession();
        return session.createQuery("FROM Event where date >= :now", Event.class)
                .setParameter("now",Instant.now())
                .getResultList();
    }

    public static List<Event> getPastEvents() {
        Session session = Connection.getSession();
        return session.createQuery("FROM Event where date < :now", Event.class)
                .setParameter("now",Instant.now())
                .getResultList();
    }

    public static List<Event> getCurrentEvents() {
        Session session = Connection.getSession();
        LocalDate localDate = LocalDate.now();

        Instant start = Instant.from(localDate.atStartOfDay());
        Instant end = Instant.from(localDate.plusDays(1).atStartOfDay());

        return session.createQuery("FROM Event where date >= :start AND date < :end", Event.class)
                .setParameter("start",start)
                .setParameter("end",end)
                .getResultList();
    }

    public static void addEvent(
            String type,
            String title,
            String comments,
            String location,
            List<Integer> userIds
    ) {
        Session session = Connection.getSession();
        Transaction tx = session.beginTransaction();
        try {
            Event event = new Event(type,title,comments,location);

            session.persist(event);

            Set<UserEvent> users = userIds.stream().map(id -> new UserEvent(new UserEventId(id,event.getId()))).collect(Collectors.toSet());
            event.setUserEvents(users);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            System.err.println(e.getMessage());
        }
    }

    public static void modifyEvent(
            int EventId,
            String type,
            String title,
            String comments,
            String location,
            List<Integer> userIds
    ) {
        Session session = Connection.getSessionIntercepted();
        Transaction tx = session.beginTransaction();
        try {
            Event event = session.get(Event.class,EventId);

            event.setType(type);
            event.setTitle(title);
            event.setComments(comments);
            event.setLocation(location);

            Set<UserEvent> users = userIds.stream().map(id -> new UserEvent(new UserEventId(id,event.getId()))).collect(Collectors.toSet());
            event.setUserEvents(users);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            System.err.println(e.getMessage());
        }
    }
}
