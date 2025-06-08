package com.treintaytres.vdc_backend.controller;

import com.treintaytres.vdc_backend.model.request.InstrumentStringRequest;
import com.treintaytres.vdc_backend.service.InstrumentStringService;
import com.treintaytres.vdc_backend.utils.Utils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/intruments_strings")
public class InstrumentStringController {

    private final InstrumentStringService instrumentStringService;

    public InstrumentStringController(InstrumentStringService instrumentStringService) {
        this.instrumentStringService = instrumentStringService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> addInstrumentString(
            @RequestPart("image") MultipartFile image,
            @RequestPart("data") InstrumentStringRequest request
    ) {
        try {

            request.setUrl(
                    Utils.saveImage(
                            image.getBytes(),
                            request.getName(),
                            "strings"
                    )
            );

            if (instrumentStringService.addInstrumentString(request)) {
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
    public ResponseEntity<Void> updateInstrumentString
            (@PathVariable int id,
             @RequestPart("image") MultipartFile image
            ) {
        try {
            String url = Utils.saveImage(
                    image.getBytes(),
                    instrumentStringService.getName(id),
                    "strings"
            );
            if (instrumentStringService.addImage(id,url)) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }

    }
}
