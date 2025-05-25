package com.treintaytres.vdc_backend.controller;

import com.treintaytres.vdc_backend.mapper.UserMapper;
import com.treintaytres.vdc_backend.model.User;
import com.treintaytres.vdc_backend.model.request.UpdateUserRequest;
import com.treintaytres.vdc_backend.response.bandInfo.Member;
import com.treintaytres.vdc_backend.service.UserService;
import com.treintaytres.vdc_backend.utils.Utils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserMapper userMapper;
    private UserService userService;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getAll();
        if (users == null || users.isEmpty()) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getUser(@PathVariable int id) {
        User user = userService.get(id);
        if (user == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(userMapper.toMember(user));
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody String email) {
        int id = userService.add(email);
        if (id != -1) {
            return ResponseEntity.ok("User created");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateUser(
            @RequestPart("image") MultipartFile image,
            @RequestPart("data") UpdateUserRequest user
    ) {
        try {
            boolean success = userService.update(
                    user.getId(),
                    user.getUsername(),
                    user.getPrimaryInstrumentId(),
                    user.getInstrumentIds(),
                    Utils.saveImage(image.getBytes(),user.getEmail())
            );
            if (success) {
                return ResponseEntity.ok("User created");
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/profile")
    public ResponseEntity<String> updateProfileImage(
            @PathVariable int id,
            @RequestPart("image") MultipartFile image,
            @RequestPart("data") String email
    ) {
        try {
            String path = Utils.saveImage(
                    image.getBytes(),
                    email
            );
            userService.updateProfileImageUrl(
                    id,
                    path
            );
            return ResponseEntity.status(201).body(path);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/permissions")
    public ResponseEntity<String> updatePermissions(@PathVariable int id, @RequestBody int permissions) {
        userService.updatePermissions(id, permissions);
        return ResponseEntity.ok("Permissions updated");
    }

    @PutMapping("/{id}/junta")
    public ResponseEntity<String> updatePerteneceJunta(@PathVariable int id) {
        userService.updatePerteneceJunta(id);
        return ResponseEntity.ok("State in junta updated");
    }

    @PutMapping("/{id}/primaryInstrument")
    public ResponseEntity<String> updatePrimaryInstrument(@PathVariable int id, @RequestBody int primaryInstrumentId) {
        userService.updatePrimaryInstrument(id, primaryInstrumentId);
        return ResponseEntity.ok("Primary instrument updated");
    }
}