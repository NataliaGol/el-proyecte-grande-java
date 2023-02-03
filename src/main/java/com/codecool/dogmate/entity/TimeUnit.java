package com.codecool.dogmate.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "time_unit")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimeUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "time_unit_id")
    private Integer id;

    private String name;

}
