package com.br.spring_security.user.validator;

import com.br.spring_security.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    public Boolean hasUserRole(User user, Integer roleId)
    {
        return user.getRoles().stream()
                .anyMatch(role -> role.getId().equals(roleId));
    }
}
