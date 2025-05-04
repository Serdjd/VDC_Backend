package com.treintaytres.vdc_backend.controller;

import com.treintaytres.vdc_backend.model.Instrument;
import com.treintaytres.vdc_backend.service.UserInstrumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userInstruments")
public class UserInstrumentController {

    private UserInstrumentService userInstrumentService;

    public UserInstrumentController(UserInstrumentService userInstrumentService) {
        this.userInstrumentService = userInstrumentService;
    }

    @PutMapping("/{id}")
    public ResponseEntity<List<Instrument>> update(@PathVariable int id, @RequestBody List<Integer> instruments) {
        List<Instrument> instrumentList = userInstrumentService.update(id,instruments);

        if (instrumentList == null || instrumentList.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(instrumentList);
    }
}
