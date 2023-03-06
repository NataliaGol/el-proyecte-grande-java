package com.codecool.dogmate.repository;

import com.codecool.dogmate.entity.Voivodeship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VoivodshipsRepository extends JpaRepository<Voivodeship, Integer> {
    @Query("SELECT DISTINCT v FROM Voivodeship v LEFT JOIN FETCH v.provinces")
    List<Voivodeship> findAllBy();
}
