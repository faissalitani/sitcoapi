package com.sitco.api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "materials")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_id")
    private Long material_id;

    @Column(name = "material_type_id")
    private int material_type_id;

    @Column(name = "brand_id")
    private int brand_id;

    @Column(name = "decor_number")
    private String decor_number;

    @Column(name = "grain_direction_id")
    private int grain_direction_id;

    @Column(name = "thickness")
    private BigDecimal thickness;

    @Column(name = "moisture_type_id")
    private int moisture_type_id;
}
