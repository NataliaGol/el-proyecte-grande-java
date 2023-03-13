package com.codecool.dogmate.repository;

import com.codecool.dogmate.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvincesRepository extends JpaRepository<Province, Integer> {

    @Query("SELECT DISTINCT d FROM Province d LEFT JOIN FETCH d.voivodeship")
    List<Province> findAllBy();
}
