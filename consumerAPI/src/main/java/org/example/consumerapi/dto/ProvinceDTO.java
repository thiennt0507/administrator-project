package org.example.consumerapi.dto;

import java.util.List;

import lombok.Data;

@Data
public class ProvinceDTO {
    private Integer id;
    private String code;
    private String fullName;
    private List<DistrictDTO> districtDTOs;
}