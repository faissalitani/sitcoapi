package com.sitco.api.repositories;

import com.sitco.api.entities.Role;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role,Byte> {
    List<Role> findAll();

    boolean existsByName(String name);
}
