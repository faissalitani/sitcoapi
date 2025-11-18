package com.sitco.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString
@Table(name = "roles")
public class Role {
    @Id
    @Column(name = "role_id", nullable = false)
    private Byte id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "description", length = 45)
    private String description;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private Set<User> users = new LinkedHashSet<>();

}