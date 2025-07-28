package com.br.spring_security.user.entity;

import com.br.spring_security.auth.dto.request.RegisterRequestDTO;
import com.br.spring_security.auth.entity.PendingUser;
import com.br.spring_security.role.entity.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Table(name = "users")
@Entity
@Setter
@Getter
@NoArgsConstructor
public class User
{
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "is_enable")
    private Boolean isEnable;

    @Column(name = "date_created_at")
    @CreationTimestamp
    private Instant dateCreatedAt;

    @Column(name = "date_updated_at")
    @UpdateTimestamp
    private Instant dateUpdatedAt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "role_id", nullable = false)
            )
    private Set<Role> roles = new HashSet<>();

    public User(RegisterRequestDTO registerRequestDTO, Role role, String passwordHash) {
        this.email = registerRequestDTO.email();
        this.password = passwordHash;
        this.name = registerRequestDTO.name();
        this.roles.add(role);
        this.isEnable = true;
    }

    public User(PendingUser pendingUser, Role role) {
        this.email = pendingUser.getEmail();
        this.password = pendingUser.getPasswordHash();
        this.name = pendingUser.getName();
        this.roles.add(role);
        this.isEnable = true;
    }
}
