package org.example.consumerapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Ward extends BaseEntity{
    @Column(
        unique = true,
        nullable = false
    )
    private String code;

    // @Column(
    //     nullable = false
    // )
    private String name;


    private  String wardType;

    @Column(
        nullable = true
    )
    private String nameEnglish;

    @ManyToOne
    @JoinColumn(name = "district_id")
    @JsonBackReference
    private District district;
}
