package com.treintaytres.vdc_backend.controller;

import com.treintaytres.vdc_backend.mapper.UserMapper;
import com.treintaytres.vdc_backend.model.New;
import com.treintaytres.vdc_backend.response.bandInfo.BandInfoResponse;
import com.treintaytres.vdc_backend.response.bandInfo.Member;
import com.treintaytres.vdc_backend.service.BandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/band")
public class BandController {

    private final UserMapper userMapper;
    private BandService bandService;

    public BandController(BandService bandService, UserMapper userMapper) {this.bandService = bandService;
        this.userMapper = userMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BandInfoResponse> getBandInfo(@PathVariable int id) {
        Boolean isAdmin = bandService.isAdmin(id);
        if (isAdmin == null) return ResponseEntity.badRequest().build();

        List<Member> members = userMapper.toMembers(bandService.getAllUsers());
        List<New> news = bandService.getAllNews();
        BandInfoResponse bandInfoResponse = new BandInfoResponse();
        bandInfoResponse.setAdmin(isAdmin);
        bandInfoResponse.setMembers(members);
        bandInfoResponse.setNews(news);
        return ResponseEntity.ok(bandInfoResponse);
    }
}
