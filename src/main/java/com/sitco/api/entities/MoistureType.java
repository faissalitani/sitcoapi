package com.sitco.api.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "moisture_types")
public class MoistureType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "moisture_type_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

}
