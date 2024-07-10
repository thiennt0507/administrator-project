package org.example.consumerapi.repository;

import java.util.List;

import org.example.consumerapi.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface DistrictRepository extends JpaRepository<District, Integer>, JpaSpecificationExecutor<District>{
    District findByNameAndDistrictType(String name, String districtType);
    District  findByCode(String code);
    List<District> findByName(String name);
}
