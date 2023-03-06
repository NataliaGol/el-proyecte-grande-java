package com.codecool.dogmate.entity;

import lombok.*;

import javax.persistence.*;
import java.util.TimeZone;

@Entity
@Table(name = "cities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_id")
    private Province province;

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

    public City(String name, Province province) {
        this.name = name;
        this.province = province;
    }
}
