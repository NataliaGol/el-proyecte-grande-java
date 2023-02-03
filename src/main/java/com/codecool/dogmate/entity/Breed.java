package com.codecool.dogmate.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "breeds")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Breed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Boolean archive = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_types")
    private AnimalType animalTypes;

}
