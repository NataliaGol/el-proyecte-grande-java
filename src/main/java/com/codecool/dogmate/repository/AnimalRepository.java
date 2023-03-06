package com.codecool.dogmate.repository;

import com.codecool.dogmate.dto.animal.AnimalDto;
import com.codecool.dogmate.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO animals(name, animal_type_id, breed_id, user_id, birth_year, is_male, picture_location, description) " +
            "VALUES (:#{#dto.name}, :#{#dto.animalTypeId}, :#{#dto.breedId}, :#{#dto.userId}, :#{#dto.birthYear}, :#{#dto.isMale}, :#{#dto.pictureLocation}, :#{#dto.description})", nativeQuery = true)
    void insertDog(AnimalDto dto);

}
