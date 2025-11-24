package com.sitco.api.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "statuses")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id")
    private Byte id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ToString.Exclude
    @OneToMany(mappedBy = "status")
    private List<CuttingJob> cuttingJobs;
}
