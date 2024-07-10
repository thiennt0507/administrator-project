package org.example.consumerapi.controller;

import java.util.List;
import java.util.Optional;

import org.example.consumerapi.dto.DistrictDTO;
import org.example.consumerapi.dto.ProvinceDTO;
import org.example.consumerapi.dto.WardDTO;
import org.example.consumerapi.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {
    @Autowired
    private ConsumerService consumerService;

    @GetMapping("/provinces")
    public List<ProvinceDTO> getAllProvinces(@RequestParam  Optional<String> name) {
        return consumerService.getProvinceCity(name);
    }

    @GetMapping("/districts")
    public List<DistrictDTO> getAllDistricts(@RequestParam  Optional<String> name) {
        return consumerService.getDistrict(name);
    }

    @GetMapping("/wards")
    public List<WardDTO> getAllWards(@RequestParam  Optional<String> name) {
        return consumerService.getWard(name);
    }
}
