package com.br.spring_security.role.validator;

import com.br.spring_security.role.entity.Role;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class RoleValidator
{
    public boolean isRolesEquals(Integer oneRoleId, Integer secondRoleId)
    {
        return oneRoleId.equals(secondRoleId);
    }

    public boolean isBasicRole(Role role)
    {
        return role.getName().equals("ROLE_BASIC");
    }
}
