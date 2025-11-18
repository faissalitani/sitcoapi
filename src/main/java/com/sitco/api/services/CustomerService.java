package com.sitco.api.services;

import com.sitco.api.entities.Customer;
import com.sitco.api.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public void addCustomer(String name, String phone, String email) {
        customerRepository.save(new Customer(name, phone, email));
    }
}
