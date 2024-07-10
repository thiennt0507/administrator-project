package org.example.producerapi.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdministrativeUnit {
    private String code;
    private String name;
    private String nameEnglish;
    private String level;
    private String district;
    private String districtCode;
    private String provinceCity;
    private String provinceCityCode;

    @Override
    public String toString() {
        return "AdministrativeUnit [code=" + code + ", name=" + name + ", nameEnglish=" + nameEnglish + ", level=" + level + ", district=" + district + ", districtCode=" + districtCode + "]";
    }
}
