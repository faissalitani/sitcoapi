package com.sitco.api.mappers;

import com.sitco.api.dtos.RoleDto;
import com.sitco.api.entities.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    public RoleDto toDto(Role role);
    public Role toEntity(RoleDto roleDto);

    @Mapping(target = "id", ignore = true)
    public void update(RoleDto roleDto, @MappingTarget Role role);
}
