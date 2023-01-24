package com.codecool.dogmate.entity;

import lombok.Getter;
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

    @Column(name = "name", length = 10)
    private String name;

}
