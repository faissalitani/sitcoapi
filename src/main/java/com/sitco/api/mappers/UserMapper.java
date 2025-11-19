package com.sitco.api.mappers;

import com.sitco.api.dtos.RegisterUserRequest;
import com.sitco.api.dtos.UpdateUserRequest;
import com.sitco.api.dtos.UserDto;
import com.sitco.api.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface UserMapper {
    @Mapping(source = "role", target = "roleDto")
    UserDto toDto(User user);

    User toEntity(RegisterUserRequest request);

    //void update(UpdateUserRequest request, @MappingTarget User user);
}
