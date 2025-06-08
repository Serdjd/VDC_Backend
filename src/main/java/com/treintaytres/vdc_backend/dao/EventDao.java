package com.treintaytres.vdc_backend.dao;

import com.treintaytres.vdc_backend.model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.String;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class EventDao {

    @PersistenceContext
    private EntityManager session;

    public List<EventWithAttendance> getAllEvents(int id) {
        return session.createQuery(
                        "select e,ue.willAttend from UserEvent ue join Event e on ue.id.idEvent = e.id " +
                                "where ue.id.idUser = :userId",
                        EventWithAttendance.class
                )
                .setParameter("userId", id)
                .getResultList();
    }

    public List<EventWithAttendance> getFutureEvents(int id) {
        return session.createQuery(
                        "select e, ue.willAttend from UserEvent ue join Event e on ue.id.idEvent = e.id " +
                                "where ue.id.idUser = :id and e.date > :now " +
                                "order by e.date asc",
                        EventWithAttendance.class
                )
                .setParameter("id", id)
                .setParameter("now", Instant.now())
                .getResultList();
    }

    public List<EventWithAttendance> getPastEvents(int id) {
        return session.createQuery(
                        "select e, ue.willAttend from UserEvent ue join Event e on ue.id.idEvent = e.id " +
                                "where ue.id.idUser = :id and e.date < :now " +
                                "order by e.date desc",
                        EventWithAttendance.class
                )
                .setParameter("id", id)
                .setParameter("now", Instant.now())
                .getResultList();
    }

    public List<EventWithAttendance> getCurrentEvents(int id) {
        LocalDate localDate = LocalDate.now();

        Instant start = Instant.from(localDate.atStartOfDay());
        Instant end = Instant.from(localDate.plusDays(1).atStartOfDay());

        return session.createQuery(
                        "select e, ue.willAttend from UserEvent ue join Event e on ue.id.idEvent = e.id " +
                                "where ue.id.idUser = :id and e.date >= :start and e.date < :end",
                        EventWithAttendance.class
                )
                .setParameter("id", id)
                .setParameter("start", start)
                .setParameter("end",end)
                .getResultList();
    }

    @Transactional
    public void addEvent(
            Integer type,
            String title,
            String comments,
            String location,
            Instant date,
            List<Integer> instrumentStringIds
    ) throws RuntimeException {
        try {
            Event event = new Event(type,title,comments,location,date, instrumentStringIds.stream().map(Object::toString).collect(Collectors.joining()));
            instrumentStringIds.forEach(System.out::println);
            session.persist(event);
            session.flush();

            Set<UserEvent> users = session.createQuery(
                    "select distinct u.id " +
                            "from User u " +
                            "join Instrument i " +
                            "on i.instrumentString.id in (:ids) and " +
                            "(u.primaryInstrument.id = i.id or i member of u.instruments) " +
                            "and u.validado = true"
                    ,Integer.class
            ).setParameter("ids", instrumentStringIds)
                    .getResultList()
                    .stream()
                    .map(id -> {
                        UserEvent ue = new UserEvent(
                                new UserEventId(event.getId(),id)
                        );
                        User user = session.getReference(User.class, id);

                        ue.setIdUser(user);
                        ue.setIdEvent(event);

                        return ue;
                    }).collect(Collectors.toSet());

            event.setUserEvents(users);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
    @Transactional
    public void modifyEvent(
            int EventId,
            Integer type,
            String title,
            String comments,
            String location,
            Instant date,
            List<Integer> instrumentStringIds
    ) throws RuntimeException {
        try {
            Event event = session.find(Event.class,EventId);

            event.setType(type);
            event.setTitle(title);
            event.setComments(comments);
            event.setLocation(location);
            event.setDate(date);
            event.setInstrumentStrings(instrumentStringIds.stream().map(Object::toString).collect(Collectors.joining()));

            Set<UserEvent> users = session.createQuery(
                            "select distinct u.id " +
                                    "from User u " +
                                    "join Instrument i " +
                                    "on i.instrumentString.id in (:ids) and " +
                                    "(u.primaryInstrument.id = i.id or i member of u.instruments) " +
                                    "and u.validado = true"
                            ,Integer.class
                    ).setParameter("ids", instrumentStringIds)
                    .getResultList()
                    .stream()
                    .map(id ->{
                        UserEvent ue = new UserEvent(
                                new UserEventId(id,event.getId())
                        );

                        User user = session.getReference(User.class, id);

                        ue.setIdUser(user);
                        ue.setIdEvent(event);

                        return ue;
                    }).collect(Collectors.toSet());

            event.setUserEvents(users);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public List<UserStadistics> getUserStadistics(int id) {
        List<UserStadistics> stadistics = session.createQuery(
                        "select e.type, SUM(case when ue.willAttend = true then 1 else 0 end) as assitance, COUNT(e) as total " +
                                "from UserEvent ue join ue.idEvent e " +
                                "where ue.id.idUser= :id and YEAR(e.date) = :year " +
                                "group by e.type " +
                                "order by e.type asc ",
                        UserStadistics.class
                )
                .setParameter("id", id)
                .setParameter("year", LocalDate.now().getYear())
                .getResultList();

        List<Integer> types = session.createQuery("select type from Type ",Integer.class).getResultList();

        if (stadistics.size() < types.size()) {
            stadistics.forEach(it -> {
                types.remove(it.type());
            });

            types.forEach(type -> {
                stadistics.add(new UserStadistics(type,0L,0L));
            });
        }
        stadistics.sort((Comparator.comparingInt(UserStadistics::type)));
        return stadistics;
    }

    public List<User> getEventUsers(int id) {
        return session
                .createQuery("SELECT ue.idUser FROM UserEvent ue WHERE ue.id.idEvent = :id",User.class)
                .setParameter("id",id)
                .getResultList();
    }

    public List<UserWithString> getEventUsersWithString(Integer id) {
        return session.createQuery(
                        "select new com.treintaytres.vdc_backend.model.UserWithString(ue.idUser, ue.willAttend) " +
                                "from UserEvent ue " +
                                "where ue.id.idEvent = :id and ue.idUser.validado = true",
                        UserWithString.class
                ).setParameter("id",id)
                .getResultList();
    }

    public Event getEvent(int id) {
        return session.find(Event.class,id);
    }
}
