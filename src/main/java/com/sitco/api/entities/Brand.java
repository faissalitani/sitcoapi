package com.sitco.api.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "brands")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "full_name")
    private String fullName;

    public Brand(String name, String fullName) {
        this.name = name;
        this.fullName = fullName;
    }
}
