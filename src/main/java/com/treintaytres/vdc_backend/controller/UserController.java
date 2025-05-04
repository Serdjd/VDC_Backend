package com.treintaytres.vdc_backend.controller;

import com.treintaytres.vdc_backend.model.User;
import com.treintaytres.vdc_backend.model.request.CreateUserRequest;
import com.treintaytres.vdc_backend.model.request.UpdateProfileData;
import com.treintaytres.vdc_backend.service.UserService;
import com.treintaytres.vdc_backend.utils.Utils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getAll();
        if (users == null || !users.isEmpty()) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        User user = userService.get(id);
        if (user == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequest user) {
        try {
            userService.add(
                    user.getUsername(),
                    user.getEmail(),
                    user.getPrimaryInstrumentId(),
                    user.getInstrumentIds(),
                    Utils.saveImage(user.getProfileImage(),user.getEmail())
            );
            return ResponseEntity.ok("User created");
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/profile")
    public ResponseEntity<String> updateProfileImage(@PathVariable int id, @RequestBody UpdateProfileData data) {
        try {
            String path = Utils.saveImage(
                    data.getProfileImage(),
                    data.getUsername()
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

    @PutMapping("/{id}/instruments")
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