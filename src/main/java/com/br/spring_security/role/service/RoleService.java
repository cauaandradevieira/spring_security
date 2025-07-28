package com.br.spring_security.role.service;

import com.br.spring_security.role.dto.request.RoleRequestDTO;
import com.br.spring_security.role.entity.Role;
import com.br.spring_security.role.exception.NoRoleFoundException;
import com.br.spring_security.role.repository.RoleRepository;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService
{
    private final RoleRepository roleRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Role> findAll()
    {
        List<Role> roleList = roleRepository.findAll();

        if(roleList.isEmpty())
        {
            throw new NoRoleFoundException("Nenhuma role cadastrada.");
        }

        return roleList;
    }

    @Override
    @Transactional(readOnly = true)
    public Role findById(@NotNull @Min(1) Integer id)
    {
        return roleRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Role não encontrada."));
    }

    @Override
    @Transactional
    public void save(RoleRequestDTO roleRequestDTO)
    {
        if(existsRole(roleRequestDTO.name()))
        {
            throw new IllegalArgumentException("Não foi possivel salvar, role ja existe.");
        }

        Role role = new Role(roleRequestDTO);
        roleRepository.save(role);
    }

    @Override
    @Transactional
    public void update(RoleRequestDTO roleRequestDTO , Integer id)
    {
        Role role = findById(id);
        role.setName(role.getName());
    }

    @Override
    @Transactional
    public void deleteById(@NotNull @Min(1) Integer id)
    {
        if(existsRole(id))
        {
            throw new IllegalArgumentException("Não foi possivel deletar, role ja existe.");
        }

        roleRepository.deleteById(id);
    }

    public Boolean existsRole(Integer id)
    {
        return roleRepository.existsById(id);
    }

    public Boolean existsRole(String name)
    {
        return roleRepository.existsByName(name);
    }
}
