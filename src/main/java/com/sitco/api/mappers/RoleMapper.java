package com.sitco.api.mappers;

import com.sitco.api.dtos.RoleDto;
import com.sitco.api.entities.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    public RoleDto toDto(Role role);
}
