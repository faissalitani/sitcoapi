package com.sitco.api.repositories;

import com.sitco.api.entities.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository <Customer, Long> {
    List<Customer> findAll();
}
