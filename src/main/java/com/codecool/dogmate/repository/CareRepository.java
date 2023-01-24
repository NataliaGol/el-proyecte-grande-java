package com.codecool.dogmate.repository;

import com.codecool.dogmate.entity.CareAnnouncement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareRepository extends JpaRepository<CareAnnouncement, Integer> {
}
