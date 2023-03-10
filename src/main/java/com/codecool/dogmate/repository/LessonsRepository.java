package com.codecool.dogmate.repository;

import com.codecool.dogmate.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonsRepository extends JpaRepository<Lesson, Integer> {
}
