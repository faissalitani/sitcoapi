package com.sitco.api.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "grain_directions")
public class GrainDirection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grain_direction_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

}
