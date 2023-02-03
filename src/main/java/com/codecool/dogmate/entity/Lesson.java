package com.codecool.dogmate.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "lessons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_id")
    private Integer id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "training_level_id")
    private TrainingLevel trainingLevel;

    @Column(name = "description")
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    @Lob
    @Column(name = "image_location")
    private String imageLocation;

    @OneToMany(mappedBy = "lesson")
    private Set<LessonStep> lessonSteps = new LinkedHashSet<>();

}
