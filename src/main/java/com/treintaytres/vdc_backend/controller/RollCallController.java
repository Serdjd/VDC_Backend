package com.treintaytres.vdc_backend.controller;

import com.treintaytres.vdc_backend.mapper.UserMapper;
import com.treintaytres.vdc_backend.model.User;
import com.treintaytres.vdc_backend.response.bandInfo.Member;
import com.treintaytres.vdc_backend.service.RollCallService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
