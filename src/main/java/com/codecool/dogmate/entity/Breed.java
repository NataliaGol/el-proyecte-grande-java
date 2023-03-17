package com.codecool.dogmate.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "breeds")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Breed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @EqualsAndHashCode.Include
    @Column(name = "name", unique = true)
    private String name;

    private Boolean archive = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_types_id")
    private AnimalType animalTypes;

    public Breed(String name) {
        this.name = name;
    }
}
