package com.codecool.dogmate.repository;

import com.codecool.dogmate.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BreedsRepository extends JpaRepository<Animal, Integer> {
}
