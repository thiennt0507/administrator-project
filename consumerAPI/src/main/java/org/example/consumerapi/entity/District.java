package org.example.consumerapi.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class District extends BaseEntity{

    @Column(
        unique = true,
        nullable = false
    )
    private String code;

    // @Column(
    //     nullable = false
    // )
    private String name;


    private  String districtType;

    @Column(
        nullable = true
    )
    private String nameEnglish;


    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "province_id")
    private Province province;

    @JsonManagedReference
    @OneToMany(mappedBy = "district")
    private List<Ward> wards;
    
}
