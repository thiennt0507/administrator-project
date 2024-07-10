package org.example.producerapi.repository;

import org.example.producerapi.model.AdministrativeUnit;

import java.util.List;

public interface AdministrativeUnitRepository  {
    void save (List<AdministrativeUnit> administrativeUnits);

    int update (AdministrativeUnit administrativeUnit);


    List<AdministrativeUnit> findAll ();

    List<AdministrativeUnit> findByName (String name);

    int deleteAll();

    int deleteById(Long id);

    AdministrativeUnit findById(Long id);
}