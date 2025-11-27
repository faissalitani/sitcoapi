package com.sitco.api.controllers;

import com.sitco.api.dtos.RoleDto;
import com.sitco.api.entities.Role;
import com.sitco.api.mappers.RoleMapper;
import com.sitco.api.repositories.RoleRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/roles")
public class RoleController {
    RoleRepository roleRepository;
    RoleMapper roleMapper;

    // CRUD Methods
    @GetMapping
    public List<RoleDto> getRoles() {
        var roles = roleRepository.findAll();
        return roles.stream().map(roleMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> getRoleById(
            @PathVariable Byte id) {
        var role = findRoleById(id);
        if (role == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(roleMapper.toDto(role));
    }

    @PostMapping
    public ResponseEntity<?> createRole(
            @Valid @RequestBody RoleDto request,
            UriComponentsBuilder uriComponentsBuilder
    ){
        if (roleRepository.existsByName(request.getName()))
            return ResponseEntity.badRequest().body(
                    Map.of("role", "Role already exists.")
            );

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
        var role = findRoleById(id);
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
        var role = findRoleById(id);
        if (role == null) {
            return ResponseEntity.notFound().build();
        }
        roleRepository.delete(role);
        return ResponseEntity.noContent().build();
    }

    // Helper Methods
    Role findRoleById (Byte id) {
        return roleRepository.findById(id).orElse(null);
    }
}
