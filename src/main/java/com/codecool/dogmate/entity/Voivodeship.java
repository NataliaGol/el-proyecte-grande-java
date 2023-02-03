package com.codecool.dogmate.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "voivodeship")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Voivodeship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "teryt_id")
    private String terytId;

    @Column(name = "name")
    private String name;

    @Column(name = "archive")
    private Boolean archive = false;

}
