package com.codecool.dogmate.entity;

import lombok.Getter;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "care_announcement_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CareAnnouncementType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "care_announcement_type_id")
    private Integer id;

    @Column(name = "name")
    private String name;

}
