package com.codecool.dogmate.entity;

import com.codecool.dogmate.mapper.Gender;
import com.codecool.dogmate.mapper.GenderConverter;
import lombok.Getter;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "animals")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_type_id")
    private AnimalType animalType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "breed_id")
    private Breed breed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "birth_year")
    private Integer birthYear;

    @Column(name = "picture_location")
    private String pictureLocation;

    @Column(name = "description")
    private String description;

    @Column(name = "is_male")
    @Convert(converter = GenderConverter.class)
    private Gender gender;

}

