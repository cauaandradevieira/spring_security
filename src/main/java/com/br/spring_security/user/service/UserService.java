package com.br.spring_security.user.service;

import com.br.spring_security.role.exception.BasicRoleImmutableException;
import com.br.spring_security.role.exception.RoleEqualsException;
import com.br.spring_security.role.service.IRoleService;
import com.br.spring_security.user.dto.request.UserRoleRequestDTO;
import com.br.spring_security.role.entity.Role;
import com.br.spring_security.user.entity.User;
import com.br.spring_security.user.exception.NoUserFoundException;
import com.br.spring_security.user.exception.UserContainsRoleException;
import com.br.spring_security.user.exception.UserNotFoundException;
import com.br.spring_security.user.exception.UserOrRoleNotFoundException;
import com.br.spring_security.user.repository.UserRepository;
import com.br.spring_security.role.validator.RoleValidator;
import com.br.spring_security.user.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements IUserService
{
    private final UserRepository userRepository;
    private final IRoleService roleService;
    private final RoleValidator roleValidator;
    private final UserValidator userValidator;

    @Override
    public List<User> findAll()
    {
        List<User> userList = userRepository.findAll();

        if(userList.isEmpty())
        {
            throw new NoUserFoundException("Nenhum usuário cadastrado no sistema.");
        }

        return userList;
    }

    @Override
    public User findById(UUID id) {
        return null;
    }

    @Override
    public User findByIdWithRole(UUID id)
    {
        return userRepository.findByIdWithRoles(id)
                .orElseThrow(() -> new UserNotFoundException("Usuario não encontrado", id));
    }

    @Override
    public User findByIdAndRole(UUID userId, Integer roleId)
    {
        return userRepository.findByIdAndRole(userId, roleId)
                .orElseThrow(() -> new UserOrRoleNotFoundException("Usuário não encontrado ou role do usuario não encontrada", userId, roleId));
    }

    @Transactional
    @Override
    public void updateUserRole(UUID id,
                               Integer oldRoleId,
                               Integer newRoleId)
    {
        if(roleValidator.isRolesEquals(oldRoleId, newRoleId))
        {
            throw new RoleEqualsException("A role escolhida é a mesma que a atual. Por favor, selecione uma role diferente.",oldRoleId,newRoleId);
        }

        Role newRole = roleService.findById(newRoleId);

        // CRIAR A EXCEÇÃO PARA ROLE DO USUARIO NAO ACHOU NO USER TALL TALL
        // exceção -> UpdatingUserRoleFailedException
        // enum -> UPDATING_USER_ROLE_FAILED
        if(roleValidator.isBasicRole(newRole))
        {
            throw new BasicRoleImmutableException("A role BASIC não pode ser atribuída manualmente, pois é automática para todos os usuários.\"");
        }

        User user = findByIdWithRole(id);

        // CRIAR A EXCEÇÃO PARA ROLE DO USUARIO NAO ACHOU NO USER TALL TALL
        // exceção -> UserRoleNotFoundException
        // enum -> USER_ROLE_NOT_FOUND
        if(!userValidator.hasUserRole(user, oldRoleId))
        {
            throw new UserOrRoleNotFoundException("Usuário não encontrado ou role do usuario não encontrada", user.getId(), oldRoleId);
        }

        if(userValidator.hasUserRole(user, newRoleId))
        {
            throw new UserContainsRoleException("Não foi possivel alterar a role do usuario, pois ele ja contem ela.",id,newRoleId);
        }

        user.getRoles().removeIf(r -> r.getId().equals(oldRoleId));
        user.getRoles().add(newRole);
    }

    @Transactional
    @Override
    public void addRoleToUser(UserRoleRequestDTO request)
    {
        User user = findByIdWithRole(request.id());

        if(userValidator.hasUserRole(user, request.newRoleId()))
        {
            throw new UserContainsRoleException("Não foi possivel adicionar a role ao usuario, pois ele ja contem ela.",request.id(),request.newRoleId());
        }

        Role newRole = roleService.findById(request.newRoleId());
        user.getRoles().add(newRole);
    }

    @Override
    public void deleteById(UUID id) {

    }

    @Override
    public Boolean existsUserAndRole(UUID userId, Integer roleId)
    {
        return userRepository.existsUserAndRole(userId, roleId);
    }


}
