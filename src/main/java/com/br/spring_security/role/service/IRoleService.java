package com.br.spring_security.role.service;

import com.br.spring_security.role.dto.request.RoleRequestDTO;
import com.br.spring_security.role.entity.Role;

import java.util.List;

public interface IRoleService
{
    List<Role> findAll();
    Role findById(Integer id);
    void save(RoleRequestDTO request);
    void update(RoleRequestDTO request, Integer id);
    void deleteById(Integer id);
    Boolean existsRole(Integer id);
    Boolean existsRole(String name);
}
