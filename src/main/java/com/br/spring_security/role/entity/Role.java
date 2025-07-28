package com.br.spring_security.role.entity;

import com.br.spring_security.role.dto.request.RoleRequestDTO;
import com.br.spring_security.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "role")
@Getter @Setter
@NoArgsConstructor
public class Role
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "date_created_at")
    @CreationTimestamp
    private Instant dateCreatedAt;

    @Column(name = "date_updated_at")
    @UpdateTimestamp
    private Instant dateUpdatedAt;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();

    public Role(RoleRequestDTO roleRequestDTO) {
        this.name = roleRequestDTO.name();
    }
}
