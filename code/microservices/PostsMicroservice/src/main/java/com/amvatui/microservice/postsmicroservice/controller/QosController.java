package com.amvatui.microservice.postsmicroservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QosController {
    @GetMapping(path = "/ping")
    public ResponseEntity<Void> ping() {
        return ResponseEntity.ok().build();
    }
}
