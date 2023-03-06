package com.codecool.dogmate.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;

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

    @Version
    private Integer version;

    @Column(name = "date_create")
    private TimeZone date_create ;

    @Column(name = "date_modify")
    private TimeZone date_modify ;

    @Column(name = "date_archive")
    private TimeZone date_archive ;

    @OneToMany(mappedBy = "voivodeship", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Set<Province> provinces = new HashSet<>();;

    public Voivodeship(String name, String terytId) {
        this.name = name;
        this.terytId = terytId;
    }

}
