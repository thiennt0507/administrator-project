package org.example.consumerapi.dto;

import java.util.List;

import lombok.Data;

@Data
public class DistrictDTO {
    private Integer id;
    private String code;
    private String fullName;
    List<WardDTO> wardDTOs;
}
