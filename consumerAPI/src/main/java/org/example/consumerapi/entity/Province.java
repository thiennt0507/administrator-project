package org.example.consumerapi.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Province extends BaseEntity{

    // @Column(
    //     unique = true,
    //     nullable = false
    // )
    private String code;

    // @Column(
    //     nullable = false
    // )
    private String name;


    private  String provinceType;

    // @Column(
    //     nullable = true
    // )
    private String nameEnglish;

    @OneToMany(mappedBy = "province")
    @JsonManagedReference
    private List<District> districts;
}
