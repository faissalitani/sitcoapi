package com.sitco.api.services;

import com.sitco.api.entities.Customer;
import com.sitco.api.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
@AllArgsConstructor
public class CuttingJobService {
    CustomerRepository customerRepository;

    public void addCuttingJob(){
        Customer customer;
    }
}
