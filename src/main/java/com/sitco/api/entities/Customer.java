package com.sitco.api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "phone", length = 45)
    private String phone;

    @Column(name = "email", length = 45)
    private String email;

    @OneToMany(mappedBy = "customer")
    private Set<CuttingJob> cuttingJobs = new HashSet<>();

    public Customer(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
}