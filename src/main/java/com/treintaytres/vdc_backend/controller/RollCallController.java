package com.treintaytres.vdc_backend.controller;

import com.treintaytres.vdc_backend.mapper.UserMapper;
import com.treintaytres.vdc_backend.model.User;
import com.treintaytres.vdc_backend.model.request.RollCallRequest;
import com.treintaytres.vdc_backend.response.bandInfo.Member;
import com.treintaytres.vdc_backend.service.RollCallService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rollcall")
public class RollCallController {


    private final UserMapper userMapper;
    private RollCallService rollCallService;

    public RollCallController(RollCallService rollCallService, UserMapper userMapper) {
        this.rollCallService = rollCallService;
        this.userMapper = userMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Member>> getRollCall(@PathVariable int id) {
        List<User> users = rollCallService.getUsers(id);
        if (users == null || users.isEmpty()) return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(userMapper.toMembers(users));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateRollCall(
            @PathVariable int id,
            @RequestBody List<RollCallRequest> requests
    ) {
        try {
            rollCallService.updateUsersAttendance(id, requests);
            return ResponseEntity.ok("success");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
