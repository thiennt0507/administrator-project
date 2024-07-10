package org.example.producerapi.repository;

import org.example.producerapi.model.AdministrativeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class JdbcAdministrativeUnitRepository  implements AdministrativeUnitRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save (List<AdministrativeUnit> administrativeUnits) {
         try {
             jdbcTemplate.batchUpdate("INSERT INTO \"administrative-units\" VALUES (?,?,?,?,?,?,?,?)",
                     administrativeUnits,
                     100
                     ,(PreparedStatement ps, AdministrativeUnit administrativeUnit) -> {
                    
                         ps.setString(1, administrativeUnit.getCode());
                         ps.setString(2, administrativeUnit.getName());
                         ps.setString(3, administrativeUnit.getNameEnglish());
                         ps.setString(4, administrativeUnit.getLevel());
                         ps.setString(5, administrativeUnit.getDistrict());
                         ps.setString(6, administrativeUnit.getDistrictCode());
                         ps.setString(7, administrativeUnit.getProvinceCity());
                         ps.setString(8, administrativeUnit.getProvinceCityCode());
                     });

         } catch (Exception e) {
             e.printStackTrace();
         }
    }

    @Override
    public int update (AdministrativeUnit administrativeUnit) {
        return jdbcTemplate.update("UPDATE administrative-units SET code=?, name=?, nameEnglish=?, level=?, district=?, districtCode=?, provinceCity=?, provinCode=?",
                new Object[] {administrativeUnit.getCode(),
                        administrativeUnit.getName(),
                        administrativeUnit.getNameEnglish(),
                        administrativeUnit.getLevel(),
                        administrativeUnit.getDistrict(),
                        administrativeUnit.getDistrictCode(),
                        administrativeUnit.getProvinceCity(),
                        administrativeUnit.getProvinceCityCode()});
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM administrative-units WHERE id=?", new Object[] {id});
    }

    @Override
    public AdministrativeUnit findById(Long id) {
        try {
            AdministrativeUnit administrativeUnit = jdbcTemplate.queryForObject("SELECT * FROM administrative-units WHERE id=?",
                    BeanPropertyRowMapper.newInstance(AdministrativeUnit.class), id);
            return administrativeUnit;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<AdministrativeUnit> findAll() {
        return jdbcTemplate.query("SELECT * FROM \"administrative-units\" ", BeanPropertyRowMapper.newInstance(AdministrativeUnit.class));
    }

    @Override
    public List<AdministrativeUnit> findByName(String name) {
        return jdbcTemplate.query("SELECT * FROM administrative-units WHERE name=?", BeanPropertyRowMapper.newInstance(AdministrativeUnit.class), name);
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE FROM administrative-units");
    }
}
