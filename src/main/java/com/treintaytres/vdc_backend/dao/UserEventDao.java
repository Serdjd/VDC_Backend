package com.treintaytres.vdc_backend.dao;

import com.treintaytres.vdc_backend.Connection;
import com.treintaytres.vdc_backend.model.Event;
import com.treintaytres.vdc_backend.model.User;
import com.treintaytres.vdc_backend.model.UserEvent;
import com.treintaytres.vdc_backend.model.UserEventId;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserEventDao {
    public static void rollCall(int eventId,Map<Integer,Boolean> attendance) {
        Session session = Connection.getSession();
        Transaction tx = session.beginTransaction();
        try {
            attendance.forEach((userId,state)->{
                UserEvent ue = session.get(UserEvent.class, new UserEventId(eventId,userId));
                ue.setAttended(state);
            });
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            System.err.println(e.getMessage());
        }
    }

    public static List<User> getUserOfEvent(int eventId) {
        Session session = Connection.getSession();
        Transaction tx = session.beginTransaction();
        try {
            Event event = session.get(Event.class, eventId);
            return event.getUserEvents().stream().map(UserEvent::getIdUser).collect(Collectors.toList());
        } catch (Exception e) {
            tx.rollback();
            System.err.println(e.getMessage());
        }
        return null;
    }

    public static void changeAttendance(int userId,int eventId, boolean attendance) {
        Session session = Connection.getSession();
        Transaction tx = session.beginTransaction();
        try {
            UserEvent ue = session.get(UserEvent.class,new UserEventId(eventId,userId));
            ue.setWillAttend(attendance);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            System.err.println(e.getMessage());
        }
    }
}
