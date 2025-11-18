package com.sitco.api.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "panels")
public class Panel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "panel_id")
    private Long id;

    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "material_id")
    private Material material;

    @Column(name = "height")
    private BigDecimal height;

    @Column(name = "width")
    private BigDecimal width;

    @Column(name = "trim_cut_height")
    private BigDecimal trimCutHeight;

    @Column(name = "trim_cut_width")
    private BigDecimal trimCutWidth;

    public Panel(Material material,
                 BigDecimal height,
                 BigDecimal width,
                 BigDecimal trimCutHeight,
                 BigDecimal trimCutWidth) {
        this.material = material;
        this.height = height;
        this.width = width;
        this.trimCutHeight = trimCutHeight;
        this.trimCutWidth = trimCutWidth;
    }

}
