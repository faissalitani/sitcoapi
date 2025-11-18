package com.sitco.api.mappers;

import com.sitco.api.dtos.UserDto;
import com.sitco.api.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
}
