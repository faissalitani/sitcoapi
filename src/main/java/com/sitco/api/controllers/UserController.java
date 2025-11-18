package com.sitco.api.controllers;

import com.sitco.api.dtos.UserDto;
import com.sitco.api.repositories.UserRepository;
import lombok.AllArgsConstructor;

import com.sitco.api.mappers.UserMapper;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @GetMapping
    public Iterable<UserDto> getAllUsers(
            @RequestParam(required = false, defaultValue = "", name = "sort") String sort
    ){
        if (!Set.of("firstName","lastName","email").contains(sort)){
            sort="firstName";
        };
        return userRepository.findAll(Sort.by(sort).descending())
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findUserById(@PathVariable Long id){
        var user = userRepository.findById(id).orElse(null);
        if(user == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userMapper.toDto(user));
    }
}
