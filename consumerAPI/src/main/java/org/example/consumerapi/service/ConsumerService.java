package org.example.consumerapi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.example.consumerapi.dto.DistrictDTO;
import org.example.consumerapi.dto.ProvinceDTO;
import org.example.consumerapi.dto.WardDTO;
import org.example.consumerapi.entity.District;
import org.example.consumerapi.entity.Province;
import org.example.consumerapi.entity.Ward;
import org.example.consumerapi.mapper.AdministrationMapper;
import org.example.consumerapi.repository.DistrictRepository;
import org.example.consumerapi.repository.ProvinceRepository;
import org.example.consumerapi.repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConsumerService {
    @Autowired
    private final ProvinceRepository provinceRepository;

    @Autowired
    private final DistrictRepository districtRepository;

    @Autowired
    private final WardRepository wardRepository;

    public List<ProvinceDTO> getProvinceCity(Optional<String> name) {
        List<Province> provinces = null;
        if (name.isPresent()) {
            name = Optional.of(slugToString(name.get()));
            provinces =  provinceRepository.findByName(name.get());
        } else {
            provinces =  provinceRepository.findAll();
        }
        List<ProvinceDTO> provinceDTOs = provinces.stream().map(AdministrationMapper::provinceToDTO).collect(Collectors.toList());
        return provinceDTOs;
    }


    public List<DistrictDTO> getDistrict(Optional<String> name){
        List<District> districts = null;
        if (name.isPresent()) {
            name = Optional.of(slugToString(name.get()));
            districts = districtRepository.findByName(name.get());
        } else {
            districts = districtRepository.findAll();
        }
        List<DistrictDTO> districtDTOs = districts.stream().map(AdministrationMapper::districtToDTO).collect(Collectors.toList());
        return districtDTOs;
    }

    public List<WardDTO> getWard(Optional<String> name) {
        List<Ward> wards = null;
        if (name.isPresent()) {
            name = Optional.of(slugToString(name.get()));
            wards = wardRepository.findByName(name.get());
        } else {
            wards = wardRepository.findAll();
        }
        List<WardDTO> wardDTOs = wards.stream().map(AdministrationMapper::wardToDTO).collect(Collectors.toList());
        return wardDTOs;
    }


    private String slugToString(final String slug) {
        String[] words = slug.split("-");
        for (int i = 0; i < words.length; i++) {
            String word =  words[i];
            words[i] = word.substring(0, 1).toUpperCase() + word.substring(1);
        }
       return String.join(" ", words);
    }
}
