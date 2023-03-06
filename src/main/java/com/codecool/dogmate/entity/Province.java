package com.codecool.dogmate.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;

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

    @Version
    private Integer version;

    @Column(name = "date_create")
    private TimeZone date_create ;

    @Column(name = "date_modify")
    private TimeZone date_modify ;

    @Column(name = "date_archive")
    private TimeZone date_archive ;

    @OneToMany(mappedBy = "province", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Set<City> cities = new HashSet<>();

    public Province(String name, String terytId, Voivodeship voivodshipId) {
        this.name = name;
        this.terytId = terytId;
        this.voivodeship = voivodshipId;
    }


}
