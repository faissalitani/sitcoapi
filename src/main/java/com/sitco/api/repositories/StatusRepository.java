package com.sitco.api.repositories;

import com.sitco.api.entities.Status;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusRepository extends JpaRepository<Status, Byte> {
    boolean existsByName(String name);
}
