package com.sitco.api.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "edgebands")
public class Edgeband {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "edgeband_id")
    private Byte id;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "thickness", precision = 3, scale = 2)
    private BigDecimal thickness;

    @Column(name = "width", precision = 4, scale = 2)
    private BigDecimal width;

    public Edgeband(String name, BigDecimal thickness, BigDecimal width) {
        this.name = name;
        this.thickness = thickness;
        this.width = width;
    }

}