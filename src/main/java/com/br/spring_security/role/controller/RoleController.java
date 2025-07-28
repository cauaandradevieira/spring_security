package com.br.spring_security.role.controller;

import com.br.spring_security.role.dto.request.RoleRequestDTO;
import com.br.spring_security.role.dto.response.RoleResponseDTO;
import com.br.spring_security.role.entity.Role;
import com.br.spring_security.role.service.IRoleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController
{
    private final IRoleService roleService;

    @GetMapping("/all")
    public ResponseEntity<?> findAll()
    {
        List<Role> roleList = roleService.findAll();
        List<RoleResponseDTO> response = roleList.stream().map(RoleResponseDTO::new).toList();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable @Positive Integer id)
    {
        Role role = roleService.findById(id);
        RoleResponseDTO response = new RoleResponseDTO(role);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping()
    public ResponseEntity<?> save(@RequestBody @Valid RoleRequestDTO RoleRequestDTO)
    {
        roleService.save(RoleRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@RequestBody @Valid RoleRequestDTO roleDto,
                                    @PathVariable @Positive Integer id)
    {
        roleService.update(roleDto, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable @Positive Integer id)
    {
        roleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
