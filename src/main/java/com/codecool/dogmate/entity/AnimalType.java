package com.codecool.dogmate.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "animal_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AnimalType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @EqualsAndHashCode.Include
    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "description")
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    @OneToMany(mappedBy = "animalTypes", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Set<Breed> breeds = new HashSet<>();

    public AnimalType(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
