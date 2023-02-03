package com.codecool.dogmate.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "provinces")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Province {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "teryt_id")
    private String terytId;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voivodeship_id")
    private Voivodeship voivodeship;

    @Column(name = "archive")
    private Boolean archive = false;

}
