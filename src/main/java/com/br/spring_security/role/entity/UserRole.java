package com.br.spring_security.role.entity;

import com.br.spring_security.role.enums.RoleEnum;
import com.br.spring_security.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;

@Getter @Setter
@Entity
@Table(name = "role")
public class UserRole
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private RoleEnum name;

    private Instant dateCreatedIn;
    private Instant dateUpdatedIn;

    @ManyToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private Set<User> users;
}
