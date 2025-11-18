package com.sitco.api.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "material_types")
public class MaterialType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_type_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column( name = "description")
    private String description;

    public MaterialType(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
