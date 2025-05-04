package com.treintaytres.vdc_backend.controller;

import com.treintaytres.vdc_backend.model.User;
import com.treintaytres.vdc_backend.service.UserEventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/userController")
public class UserEventController {

    private UserEventService userEventService;

    public UserEventController(UserEventService userEventService) {
        this.userEventService = userEventService;
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> rollCall(@PathVariable int id, @RequestBody Map<Integer, Boolean> attendance) {
        userEventService.rollCall(id,attendance);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<User>> getUserEvent(@PathVariable int id) {
        List<User> users = userEventService.getUserOfEvent(id);
        if (users == null) return ResponseEntity.notFound().build();
        else if (users.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{user_id}/{event_id}")
    public ResponseEntity<String> updateUserAttendance(@PathVariable int user_id, @PathVariable int event_id, @RequestBody Boolean attendance) {
        userEventService.changeAttendance(user_id,event_id,attendance);
        return ResponseEntity.ok("success");
    }
}
