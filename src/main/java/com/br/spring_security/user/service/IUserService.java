package com.br.spring_security.user.service;

import com.br.spring_security.user.dto.request.UserRoleRequestDTO;
import com.br.spring_security.user.entity.User;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    List<User> findAll();
    User findById(UUID id);
    User findByIdWithRole(UUID id);
    User findByIdAndRole(UUID userId, Integer roleId);
    void updateUserRole(UUID id, Integer oldRoleId, Integer newRoleId);
    void addRoleToUser(UserRoleRequestDTO request);
    void deleteById(UUID id);
    Boolean existsUserAndRole(UUID userId, Integer roleId);
}
