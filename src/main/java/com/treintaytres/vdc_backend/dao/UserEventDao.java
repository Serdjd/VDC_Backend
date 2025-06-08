package com.treintaytres.vdc_backend.dao;

import com.treintaytres.vdc_backend.model.*;
import com.treintaytres.vdc_backend.model.request.RollCallRequest;
import com.treintaytres.vdc_backend.response.bandInfo.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class UserEventDao {

    @PersistenceContext
    private EntityManager session;

    @Transactional
    public void rollCall(int id, List<RollCallRequest> requests) throws RuntimeException {
        try {
            requests.forEach(member-> {
                UserEvent ue = session.find(UserEvent.class, new UserEventId(id,member.getId()));
                ue.setAttended(member.getAttendance());
            });
            Event event = session.find(Event.class, id);
            event.setRollCallMaked(true);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public List<User> getUserOfEvent(int eventId) {
        try {
            Event event = session.find(Event.class, eventId);
            return event.getUserEvents().stream().map(UserEvent::getIdUser).collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public void changeAttendance(int userId,int eventId, boolean attendance) {
        try {
            UserEvent ue = session.find(UserEvent.class,new UserEventId(eventId,userId));
            ue.setWillAttend(attendance);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public void addUser(int userId) throws RuntimeException {
        try {
            User user = session.find(User.class, userId);
            List<Integer> instrumentStringIds = new ArrayList<>();

            instrumentStringIds.add(user.getPrimaryInstrument().getInstrumentString().getId());

            instrumentStringIds.addAll(
                    user.getInstruments().stream().map(
                            instrument -> instrument.getInstrumentString().getId()
                    ).toList()
            );

            session.createQuery("from Event where date > :now", Event.class)
                    .setParameter("now", Instant.now())
                    .getResultList().forEach(event -> {
                        List<Integer> ids = Arrays.stream(event.getInstrumentStrings().split(",")).mapToInt(Integer::parseInt).boxed().toList();
                        if (!Collections.disjoint(ids, instrumentStringIds)) {
                            UserEvent ue = new UserEvent();
                            UserEventId userEventId = new UserEventId(event.getId(),userId);
                            ue.setId(userEventId);
                            ue.setIdEvent(event);
                            ue.setIdUser(user);
                            session.persist(ue);
                        }
                    });
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
