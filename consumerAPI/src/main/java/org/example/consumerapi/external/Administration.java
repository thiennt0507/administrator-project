package org.example.consumerapi.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Administration {
    private String code;
    private String name;
    private String nameEnglish;
    private String level;
    private String district;
    private String districtCode;
    private String provinceCity;
    private String provinceCityCode;
}
