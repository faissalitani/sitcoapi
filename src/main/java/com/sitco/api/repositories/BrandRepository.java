package com.sitco.api.repositories;

import com.sitco.api.entities.Brand;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BrandRepository extends CrudRepository<Brand, Integer> {
    List<Brand> findAll();
}
