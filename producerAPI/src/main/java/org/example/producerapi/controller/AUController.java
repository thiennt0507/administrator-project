package org.example.producerapi.controller;

import org.example.producerapi.service.AUService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.CompletableFuture;

@RestController
public class AUController {
    private final AUService auService;

    public AUController(AUService auService) {
        this.auService = auService;
    }

    @PostMapping(value = "/administrative-units", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<HttpStatus> createAdministrativeUnit(@RequestParam(value = "files") MultipartFile[] files) {
        for (MultipartFile file : files) {
            auService.createAdministrativeUnit(file);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/administrative-units", produces = {MediaType.APPLICATION_JSON_VALUE})
    public CompletableFuture<ResponseEntity> findAllAdministrativeUnits() {
//        return CompletableFuture.supplyAsync(() -> ResponseEntity.ok(auService.findAllAdministrativeUnits()));
        return auService.findAllAdministrativeUnits().thenApply(ResponseEntity::ok);
    }
}
