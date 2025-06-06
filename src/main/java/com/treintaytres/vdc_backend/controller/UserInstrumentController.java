package com.treintaytres.vdc_backend.controller;

import com.treintaytres.vdc_backend.service.UserInstrumentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userInstruments")
public class UserInstrumentController {

    private UserInstrumentService userInstrumentService;

    public UserInstrumentController(UserInstrumentService userInstrumentService) {
        this.userInstrumentService = userInstrumentService;
    }
}
