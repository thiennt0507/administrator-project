package org.example.consumerapi.repository;

import java.util.List;

import org.example.consumerapi.entity.Ward;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WardRepository extends JpaRepository<Ward, Integer> {
    List<Ward> findByName(String name);
}
