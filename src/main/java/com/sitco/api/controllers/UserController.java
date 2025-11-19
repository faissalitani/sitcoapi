package com.sitco.api.controllers;

import com.sitco.api.dtos.RegisterUserRequest;
import com.sitco.api.dtos.UpdateUserRequest;
import com.sitco.api.dtos.UserDto;
import com.sitco.api.repositories.RoleRepository;
import com.sitco.api.repositories.UserRepository;
import lombok.AllArgsConstructor;

import com.sitco.api.mappers.UserMapper;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    @GetMapping
    public Iterable<UserDto> getAllUsers(
            @RequestHeader(required = false, name = "x-auth-token")  String authToken,
            @RequestParam(required = false, defaultValue = "", name = "sort") String sort
    ){
        System.out.println(authToken);
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

    @PostMapping
    public ResponseEntity<UserDto> createUser(
            @RequestBody RegisterUserRequest request,
            UriComponentsBuilder uriBuilder
    ){
        var user = userMapper.toEntity(request);
        user.setRole(roleRepository.findById(request.getRoleId()).orElse(null));
        userRepository.save(user);

        var userDto = userMapper.toDto(user);
        var uri = uriBuilder.path("/users/{id}").buildAndExpand(userDto.getId()).toUri();
        return ResponseEntity.created(uri).body(userDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable(name = "id") Long id,
            @RequestBody UpdateUserRequest request) {
            var user = userRepository.findById(id).orElse(null);
            if(user == null){
                return ResponseEntity.notFound().build();
            }

            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setEmail(request.getEmail());
            user.setRole(roleRepository.findById(request.getRoleId()).orElse(null));
            userRepository.save(user);

            return ResponseEntity.ok(userMapper.toDto(user));
    }
}
