package com.sitco.api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "moisture_types")
public class MoistureType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "moisture_type_id")
    private int moisture_type_id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

}
