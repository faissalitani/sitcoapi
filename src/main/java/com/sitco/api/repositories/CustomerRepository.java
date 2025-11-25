package com.sitco.api.repositories;

import com.sitco.api.entities.Customer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository <Customer, Long> {
    List<Customer> findAll();

    boolean existsByPhone(String phone);
}
