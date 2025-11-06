package com.sitco.api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "edgebands")
public class Edgeband {
    @Id
    @Column(name = "edgeband_id")
    private Byte id;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "thickness", precision = 3, scale = 2)
    private BigDecimal thickness;

    @Column(name = "width", precision = 4, scale = 2)
    private BigDecimal width;

}