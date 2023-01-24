package com.codecool.dogmate.entity;

import lombok.Getter;
import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "training_levels")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainingLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "training_level_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "trainingLevel")
    private Set<Lesson> lessons = new LinkedHashSet<>();

}
