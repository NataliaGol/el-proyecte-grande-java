package com.codecool.dogmate.repository;

import com.codecool.dogmate.entity.TrainingLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepository extends JpaRepository<TrainingLevel, Integer> {
}
