package com.sitco.api.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "materials")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "material_type_id")
    private MaterialType materialType;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Column(name = "decor_number")
    private String decorNumber;

    @ManyToOne
    @JoinColumn(name = "grain_direction_id")
    private GrainDirection grainDirection;

    @Column(name = "thickness")
    private BigDecimal thickness;

    @ManyToOne
    @JoinColumn(name = "moisture_type_id")
    private MoistureType moistureType;

    @OneToMany(mappedBy = "material")
    @ToString.Exclude
    @Builder.Default
    private List<Panel> panels = new ArrayList<>();

    public void addPanel(Panel panel) {
        panels.add(panel);
        panel.setMaterial(this);
    }
}
