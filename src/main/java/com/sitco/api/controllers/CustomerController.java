package com.sitco.api.controllers;

import com.sitco.api.dtos.CustomerDto;
import com.sitco.api.entities.Customer;
import com.sitco.api.mappers.CustomerMapper;
import com.sitco.api.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/customers")
public class CustomerController {
    CustomerRepository customerRepository;
    CustomerMapper customerMapper;

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return ResponseEntity.ok().body(customers.stream().map(customerMapper::toDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(
            @PathVariable Long id
    ){
        var customer = customerRepository.findById(id).orElse(null);
        if(customer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(customerMapper.toDto(customer));
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(
            @RequestBody CustomerDto request,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        var customer = customerMapper.toEntity(request);
        customerRepository.save(customer);

        var customerDto = customerMapper.toDto(customer);
        var uri = uriComponentsBuilder.path("/customers/{id}").buildAndExpand(customerDto.getId()).toUri();
        return ResponseEntity.created(uri).body(customerDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(
            @RequestBody CustomerDto customerDto,
            @PathVariable Long id
    ){
        var customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        customerMapper.update(customerDto, customer);
        customerRepository.save(customer);
        customerDto.setId(customer.getId());
        return ResponseEntity.ok().body(customerDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>  deleteCustomer(
            @PathVariable Long id
    ){
        var customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        customerRepository.delete(customer);
        return ResponseEntity.noContent().build();
    }


}
