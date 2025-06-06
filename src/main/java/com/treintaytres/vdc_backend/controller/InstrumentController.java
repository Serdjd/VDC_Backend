package com.treintaytres.vdc_backend.controller;

import com.treintaytres.vdc_backend.mapper.UserMapper;
import com.treintaytres.vdc_backend.model.request.InstrumentRequest;
import com.treintaytres.vdc_backend.response.instrument.InstrumentsResponse;
import com.treintaytres.vdc_backend.service.InstrumentService;
import com.treintaytres.vdc_backend.utils.Utils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/instruments")
public class InstrumentController {

    private final UserMapper userMapper;
    private InstrumentService instrumentService;

    public InstrumentController(InstrumentService instrumentService, UserMapper userMapper) {
        this.instrumentService = instrumentService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public ResponseEntity<InstrumentsResponse> getAllInstruments() {
        InstrumentsResponse response = new InstrumentsResponse(
                userMapper.toInstruments(instrumentService.getAllInstruments())
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> addInstrumentString(
            @RequestPart("image") MultipartFile image,
            @RequestPart("data") InstrumentRequest request
    ) {
        try {

            request.setUrl(
                    Utils.saveImage(
                            image.getBytes(),
                            request.getName(),
                            "instruments"
                    )
            );

            if (instrumentService.addInstrument(request)) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(path = "/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateInstrumentString(
            @PathVariable int id,
            @RequestPart("image") MultipartFile image
    ) {
        try {
            String url = Utils.saveImage(
                    image.getBytes(),
                    instrumentService.getName(id),
                    "instruments"
            );
            if (instrumentService.addImage(id,url)) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }

    }
}
