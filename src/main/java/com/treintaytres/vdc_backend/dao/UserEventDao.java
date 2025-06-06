package com.treintaytres.vdc_backend.dao;

import com.treintaytres.vdc_backend.model.Event;
import com.treintaytres.vdc_backend.model.User;
import com.treintaytres.vdc_backend.model.UserEvent;
import com.treintaytres.vdc_backend.model.UserEventId;
import com.treintaytres.vdc_backend.model.request.RollCallRequest;
import com.treintaytres.vdc_backend.response.bandInfo.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
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
}
