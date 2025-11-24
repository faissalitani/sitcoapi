package com.sitco.api.controllers;

import com.sitco.api.dtos.RoleDto;
import com.sitco.api.entities.Role;
import com.sitco.api.mappers.RoleMapper;
import com.sitco.api.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/roles")
public class RoleController {
    RoleRepository roleRepository;
    RoleMapper roleMapper;

    @GetMapping
    public List<RoleDto> getRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(roleMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> getRoleById(
            @PathVariable Byte id) {
        var role = roleRepository.findById(id).orElse(null);
        if (role == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(roleMapper.toDto(role));
    }

    @PostMapping
    public ResponseEntity<RoleDto> createRole(
            @RequestBody RoleDto request,
            UriComponentsBuilder uriComponentsBuilder
            ){
        var role = roleMapper.toEntity(request);
        roleRepository.save(role);

        var roleDto = roleMapper.toDto(role);
        var uri = uriComponentsBuilder.path("/roles/{id}").buildAndExpand(roleDto.getId()).toUri();
        return ResponseEntity.created(uri).body(roleDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDto> updateRole(
            @PathVariable Byte id,
            @RequestBody RoleDto roleDto
    ){
        var role = roleRepository.findById(id).orElse(null);
        if (role == null) {
            return ResponseEntity.notFound().build();
        }
        roleMapper.update(roleDto, role);
        roleRepository.save(role);
        roleDto.setId(role.getId());

        return ResponseEntity.ok().body(roleDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(
            @PathVariable Byte id
    ){
        var role = roleRepository.findById(id).orElse(null);
        if (role == null) {
            return ResponseEntity.notFound().build();
        }
        roleRepository.delete(role);
        return ResponseEntity.noContent().build();
    }
}
