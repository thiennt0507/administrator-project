package org.example.consumerapi.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.example.consumerapi.dto.DistrictDTO;
import org.example.consumerapi.dto.ProvinceDTO;
import org.example.consumerapi.dto.WardDTO;
import org.example.consumerapi.entity.District;
import org.example.consumerapi.entity.Province;
import org.example.consumerapi.entity.Ward;

public class AdministrationMapper {
    public static ProvinceDTO provinceToDTO(Province province) {
        ProvinceDTO provinceDTO = new ProvinceDTO();
        provinceDTO.setId(province.getId());
        provinceDTO.setCode(province.getCode());
        provinceDTO.setFullName(province.getProvinceType() + " " + province.getName());

        List<DistrictDTO> districtDTOs = province.getDistricts().stream().map(AdministrationMapper::districtToDTO).collect(Collectors.toList());
        
        provinceDTO.setDistrictDTOs(districtDTOs);
        return provinceDTO;
    }

    public static  DistrictDTO districtToDTO(District district) {
        DistrictDTO districtDTO = new DistrictDTO();

        districtDTO.setId(district.getId());
        districtDTO.setCode(district.getCode());
        districtDTO.setFullName(district.getDistrictType() + " " + district.getName());
        List<WardDTO> wardDTOs = district.getWards().stream().map(AdministrationMapper::wardToDTO).collect(Collectors.toList());

        districtDTO.setWardDTOs(wardDTOs);
        return districtDTO;
    }


    public static  WardDTO wardToDTO(Ward ward) {
        WardDTO wardDTO = new WardDTO();
        wardDTO.setId(ward.getId());
        wardDTO.setCode(ward.getCode());
        wardDTO.setFullName(ward.getWardType() + " " + ward.getName());

        return wardDTO;
    }
}
