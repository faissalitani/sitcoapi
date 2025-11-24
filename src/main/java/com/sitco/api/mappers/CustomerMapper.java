package com.sitco.api.mappers;

import com.sitco.api.dtos.CustomerDto;
import com.sitco.api.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDto toDto(Customer customer);

    Customer toEntity(CustomerDto customerDto);

    @Mapping(target = "id", ignore = true)
    void update(CustomerDto customerDto, @MappingTarget Customer customer);
}
