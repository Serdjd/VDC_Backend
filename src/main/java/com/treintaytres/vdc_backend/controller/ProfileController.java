package com.treintaytres.vdc_backend.controller;

import com.treintaytres.vdc_backend.mapper.UserMapper;
import com.treintaytres.vdc_backend.model.New;
import com.treintaytres.vdc_backend.model.User;
import com.treintaytres.vdc_backend.response.bandInfo.BandInfoResponse;
import com.treintaytres.vdc_backend.response.bandInfo.Instrument;
import com.treintaytres.vdc_backend.response.bandInfo.Member;
import com.treintaytres.vdc_backend.response.profile.ProfileResponse;
import com.treintaytres.vdc_backend.service.BandService;
import com.treintaytres.vdc_backend.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final UserMapper userMapper;
    private ProfileService profileService;

    public ProfileController(ProfileService profileService, UserMapper userMapper) {
        this.profileService = profileService;
        this.userMapper = userMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponse> getProfile(@PathVariable int id) {
        User user = profileService.getProfile(id);
        if (user == null) return ResponseEntity.badRequest().build();

        ProfileResponse profileResponse = new ProfileResponse();

        profileResponse.setName(user.getUsername());
        profileResponse.setUrl(user.getProfileImageUrl());

        List<Instrument> instruments = new ArrayList<>(userMapper.toInstruments(user.getInstruments().stream().toList()));
        instruments.addFirst(userMapper.toInstrument(user.getPrimaryInstrument()));

        profileResponse.setInstruments(instruments);
        profileResponse.setStats(profileService.getAllUserStadistics(id));
        return ResponseEntity.ok(profileResponse);
    }
}
