package com.sitco.api.controllers;

import com.sitco.api.dtos.ChangePasswordRequest;
import com.sitco.api.dtos.RegisterUserRequest;
import com.sitco.api.dtos.UserDto;
import com.sitco.api.mappers.RoleMapper;
import com.sitco.api.repositories.RoleRepository;
import com.sitco.api.repositories.UserRepository;
import lombok.AllArgsConstructor;

import com.sitco.api.mappers.UserMapper;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
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
    private final RoleMapper roleMapper;

    @GetMapping
    public Iterable<UserDto> getAllUsers(
            @RequestHeader(required = false, name = "x-auth-token")  String authToken,
            @RequestParam(required = false, defaultValue = "", name = "sort") String sort
    ){
        System.out.println(authToken);
        if (!Set.of("firstName","lastName","email").contains(sort)){
            sort="firstName";
        }
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
        var userDto = userMapper.toDto(user);
        userDto.setRoleId(user.getRole().getId());

        return ResponseEntity.ok(userDto);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(
            @RequestBody RegisterUserRequest request,
            UriComponentsBuilder uriBuilder
    ){
        var role = roleRepository.findById(request.getRoleId()).orElse(null);
        if(role == null){
            return ResponseEntity.notFound().build();
        }

        var user = userMapper.toEntity(request);
        user.setRole(role);
        userRepository.save(user);

        var userDto = userMapper.toDto(user);
        var uri = uriBuilder.path("/users/{id}").buildAndExpand(userDto.getId()).toUri();
        return ResponseEntity.created(uri).body(userDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable(name = "id") Long id,
            @RequestBody UserDto userDto
        ){
            var user = userRepository.findById(id).orElse(null);
            if(user == null){
                return ResponseEntity.notFound().build();
            }

            var role = roleRepository.findById(userDto.getRoleId()).orElse(null);
            if(role == null){
                return ResponseEntity.badRequest().build();
            }

            userMapper.update(userDto,user);
            user.setRole(role);
            userRepository.save(user);
            userDto.setRoleId(role.getId());
            userDto.setRoleDto(roleMapper.toDto(role));


            return ResponseEntity.ok(userDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        var user = userRepository.findById(id).orElse(null);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        userRepository.delete(user);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/change-password")
    public ResponseEntity<Void> changePassword(
            @PathVariable Long id,
            @RequestBody ChangePasswordRequest request
    ){
        var user = userRepository.findById(id).orElse(null);
        if(user == null){
            return ResponseEntity.notFound().build();
        }

        if (!user.getPassword().equals(request.getOldPassword())){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        user.setPassword(request.getNewPassword());
        userRepository.save(user);
        return ResponseEntity.noContent().build();
    }
}
