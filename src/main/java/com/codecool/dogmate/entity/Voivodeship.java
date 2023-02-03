package com.codecool.dogmate.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "voivodeship")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Voivodeship {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "teryt_id")
    private String terytId;

    @Column(name = "name")
    private String name;

}