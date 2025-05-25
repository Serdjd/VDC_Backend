package com.treintaytres.vdc_backend.dao;

import com.treintaytres.vdc_backend.Connection;
import com.treintaytres.vdc_backend.model.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.lang.String;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EventDao {

    public static List<EventWithAttendance> getAllEvents(int id) {
        Session session = Connection.getSession();

        return session.createQuery(
                        "select e,ue.willAttend from UserEvent ue join Event e on ue.id.idEvent = e.id " +
                                "where ue.id.idUser = :userId",
                        EventWithAttendance.class
                )
                .setParameter("userId", id)
                .getResultList();
    }

    public static List<EventWithAttendance> getFutureEvents(int id) {
        Session session = Connection.getSession();
        return session.createQuery(
                        "select e, ue.willAttend from UserEvent ue join Event e on ue.id.idEvent = e.id " +
                                "where ue.id.idUser = :id and e.date > :now",
                        EventWithAttendance.class
                )
                .setParameter("id", id)
                .setParameter("now", Instant.now())
                .getResultList();
    }

    public static List<EventWithAttendance> getPastEvents(int id) {
        Session session = Connection.getSession();
        return session.createQuery(
                        "select e, ue.willAttend from UserEvent ue join Event e on ue.id.idEvent = e.id " +
                                "where ue.id.idUser = :id and e.date < :now",
                        EventWithAttendance.class
                )
                .setParameter("id", id)
                .setParameter("now", Instant.now())
                .getResultList();
    }

    public static List<EventWithAttendance> getCurrentEvents(int id) {
        Session session = Connection.getSession();
        LocalDate localDate = LocalDate.now();

        Instant start = Instant.from(localDate.atStartOfDay());
        Instant end = Instant.from(localDate.plusDays(1).atStartOfDay());

        return session.createQuery(
                        "select e, ue.willAttend from UserEvent ue join Event e on ue.id.idEvent = e.id " +
                                "where ue.id.idUser = :id and e.date >= :now and e.date < :end",
                        EventWithAttendance.class
                )
                .setParameter("id", id)
                .setParameter("start", start)
                .setParameter("end",end)
                .getResultList();
    }

    public static void addEvent(
            Integer type,
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
            Integer type,
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

    public static List<UserStadistics> getUserStadistics(int id) {
        Session session = Connection.getSession();
        return session.createQuery(
                        "select e.type, SUM(case when ue.willAttend = true then 1 else 0 end) as assitance, COUNT(e) as total " +
                                "from UserEvent ue join ue.idEvent e " +
                                "where ue.id.idUser= :id and YEAR(e.date) = :year " +
                                "group by e.type",
                        UserStadistics.class
                )
                .setParameter("id", id)
                .setParameter("year", LocalDate.now().getYear())
                .getResultList();
    }

    public static List<User> getEventUsers(int id) {
        Session session = Connection.getSession();
        return session
                .createQuery("SELECT ue.idUser FROM UserEvent ue WHERE ue.id.idEvent = :id",User.class)
                .setParameter("id",id)
                .getResultList();
    }

    public static List<UserWithString> getEventUsersWithString(Integer id) {
        Session session = Connection.getSession();
        return session.createQuery(
                        "select new com.treintaytres.vdc_backend.model.UserWithString(ue.idUser, ue.willAttend) " +
                                "from UserEvent ue " +
                                "where ue.id.idEvent = :id",
                        UserWithString.class
                ).setParameter("id",id)
                .getResultList();
    }

    public static Event getEvent(int id) {
        Session session = Connection.getSession();
        return session.get(Event.class,id);
    }
}
