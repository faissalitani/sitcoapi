package com.sitco.api.repositories;

import com.sitco.api.entities.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository <Customer, Long> {
}
