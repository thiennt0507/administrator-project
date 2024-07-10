package org.example.consumerapi.controller;

import org.example.consumerapi.service.ImportDataService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping(value = "/consumer")
public class ImportDataController {
    private final ImportDataService importDataService;
    
    public ImportDataController(ImportDataService importDataService) {
        this.importDataService = importDataService;
    }

    @GetMapping(value = "/import-data")
    public ResponseEntity<HttpStatus> loadDataToDatabase() {
        importDataService.loadAdministrationToDatabase();
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
