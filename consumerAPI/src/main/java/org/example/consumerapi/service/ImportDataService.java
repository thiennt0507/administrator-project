package org.example.consumerapi.service;

import java.util.List;

import org.example.consumerapi.external.Administration;
import org.example.consumerapi.repository.DistrictRepository;
import org.example.consumerapi.repository.ProvinceRepository;
import org.example.consumerapi.repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.example.consumerapi.entity.District;
import org.example.consumerapi.entity.Province;
import org.example.consumerapi.entity.Ward;
import org.example.consumerapi.enums.ClassifyAdministrationEnum;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImportDataService {
    @Autowired
    private final ProvinceRepository provinceRepository;

    @Autowired
    private final DistrictRepository districtRepository;

    @Autowired
    private final WardRepository wardRepository;

    @Autowired
    private RestTemplate restTemplate;

    public void loadAdministrationToDatabase() {
        ResponseEntity<List<Administration>> administrationResponse =  this.restTemplate.exchange("http://localhost:8080/administrative-units" ,HttpMethod.GET, null, new ParameterizedTypeReference<List<Administration>>() {});
        List<Administration> administrations = administrationResponse.getBody();


        for (Administration administration : administrations) {
            analyseAdministration(administration);
        }

    }

    private ClassifyAdministrationEnum classifyAdministration(Administration administration) {
        if (administration.getDistrictCode().isEmpty()) {
            if (administration.getProvinceCityCode().isEmpty())
                return ClassifyAdministrationEnum.PROVINCE;
            
            return ClassifyAdministrationEnum.DISTRICT;
        }

        return ClassifyAdministrationEnum.WARD;
    }

    private void analyseAdministration(Administration administration) {
        ClassifyAdministrationEnum classifyAdministration = classifyAdministration(administration);
        System.out.println(administration);
        String[] fullName = sliceName(administration.getName());  // [0] is the type of administration and [1] is the name of administration
        switch( classifyAdministration) {
            case PROVINCE:
                Province province = new Province(administration.getCode(), fullName[1], fullName[0], administration.getNameEnglish(), null);
                provinceRepository.save(province);
                break;
            case DISTRICT:
                Province province1 = provinceRepository.findByCode(administration.getProvinceCityCode());
                District district1 = new District(administration.getCode(), fullName[1], fullName[0], administration.getNameEnglish(), province1, null);
                districtRepository.save(district1);
                break;
            case WARD:
                District district = districtRepository.findByCode(administration.getDistrictCode());
                Ward ward = new Ward(administration.getCode(), fullName[1], fullName[0], administration.getNameEnglish(), district);
                wardRepository.save(ward);
                break;
            default:
                break;
        }
    }

    private String[] sliceName(String name) {
        String[] baseStrings  = {"Thành phố", "Tỉnh", "Quận", "Huyện", "Thị trấn", "Thị xã", "Phường", "Xã"};
        String[] reponse = new String[2];
        for (String baseString : baseStrings) {
            if (name.toLowerCase().contains(baseString.toLowerCase())) {
                reponse[0] = baseString;
                reponse[1] = name.substring(baseString.length() + 1);
                break;
            }
        }
        
        return reponse;
    }
}
