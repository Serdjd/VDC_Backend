package com.treintaytres.vdc_backend.controller;

import com.treintaytres.vdc_backend.mapper.UserMapper;
import com.treintaytres.vdc_backend.model.User;
import com.treintaytres.vdc_backend.model.request.*;
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
    public ResponseEntity<List<Member>> getUsers(@RequestParam String status) {
        if (status == null || status.isEmpty()) {
            List<User> users = userService.getAll();
            if (users == null || users.isEmpty()) return ResponseEntity.badRequest().build();
            return ResponseEntity.ok(userMapper.toMembers(users));
        }
        List<User> users = userService.getAllNotValidate();
        if (users == null) return ResponseEntity.badRequest().build();
        if (users.isEmpty()) return ResponseEntity.noContent().build();
        List<Member> members = users.stream().map(u -> new Member(u.getId(),u.getEmail())).toList();
        return ResponseEntity.ok(members);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getUser(@PathVariable int id) {
        User user = userService.get(id);
        if (user == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(userMapper.toMember(user));
    }

    @GetMapping("/id/{email}")
    public ResponseEntity<Integer> getUser(@PathVariable String email) {
        User user = userService.get(email);
        if (user == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user.getId());
    }

    @PostMapping
    public ResponseEntity<Integer> createUser(@RequestBody EmailUserRequest request) {
        int id = userService.add(request.getEmail());
        if (id != -1) {
            return ResponseEntity.ok(id);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(path = "/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateUser(
            @PathVariable int id,
            @RequestPart("image") MultipartFile image,
            @RequestPart("data") UpdateUserRequest request
    ) {
        try {
            User user = userService.get(id);
            String url = Utils.saveImage(image.getBytes(),user.getEmail());
            boolean success = userService.update(
                    id,
                    request.getUsername(),
                    request.getPrimaryInstrumentId(),
                    request.getInstrumentIds(),
                    url
            );
            userService.addUserEvents(id);
            if (success) {
                return ResponseEntity.ok(url);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/validate/{id}")
    public ResponseEntity<Boolean> validateUser(@PathVariable int id) {
        User user = userService.get(id);
        if (user == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(user.getValidado());
    }

    @GetMapping("/registry_completed/{id}")
    public ResponseEntity<Boolean> registryCompleted(@PathVariable int id) {
        User user = userService.get(id);
        if (user == null) return ResponseEntity.notFound().build();
        boolean response = (user.getValidado() && user.getPrimaryInstrument() != null && user.getProfileImageUrl() != null);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/validate/{id}")
    public ResponseEntity<Void> validateUser(@PathVariable int id, @RequestBody UserValidateRequest request) {
        if (userService.updateValidation(id,request.isValidate())) {
            return ResponseEntity.ok().build();
        } else return ResponseEntity.badRequest().build();
    }

    @PutMapping("/profile/{id}")
    public ResponseEntity<String> updateProfileImage(
            @PathVariable int id,
            @RequestPart("image") MultipartFile image
    ) {
        try {
            User user = userService.get(id);

            String path = Utils.saveImage(
                    image.getBytes(),
                    user.getEmail()
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

    @PutMapping("/permissions/{id}")
    public ResponseEntity<String> updatePermissions(@PathVariable int id, @RequestBody PermissionsUserRequest request) {
        userService.updatePermissions(id, request.getPermissions());
        return ResponseEntity.ok("Permissions updated");
    }

    @GetMapping("/permissions/{id}")
    public ResponseEntity<Integer> permissions(@PathVariable int id) {
        User user = userService.get(id);
        if (user == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user.getRol());
    }

    @PutMapping("/junta/{id}")
    public ResponseEntity<String> updatePerteneceJunta(@PathVariable int id) {
        userService.updatePerteneceJunta(id);
        return ResponseEntity.ok("State in junta updated");
    }

    @PutMapping("/instruments/{id}")
    public ResponseEntity<String> updateInstruments(@PathVariable int id, @RequestBody InstrumentsUserRquest request) {
        userService.updateInstruments(
                id,
                request.getPrimaryInstrumentId(),
                request.getInstrumentIds()
        );
        return ResponseEntity.ok("Instruments updated");
    }
}