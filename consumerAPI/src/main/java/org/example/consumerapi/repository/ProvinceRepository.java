package org.example.consumerapi.repository;

import java.util.List;

import org.example.consumerapi.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProvinceRepository extends JpaRepository<Province, Integer> {
    Province findByCode(String code);
    List<Province> findByName(String name);
}
