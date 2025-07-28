package com.br.spring_security.auth.entity;

import com.br.spring_security.auth.dto.request.RegisterRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "pending_user")
@Getter @Setter
@NoArgsConstructor
public class PendingUser
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "confirmation_code")
    private String confirmationCode;

    @Column(name = "date_expiration")
    private Instant dateExpiration;

    public PendingUser(RegisterRequestDTO registerRequestDTO, String passwordHash, String confirmation_code, Instant date_expiration) {
        this.name = registerRequestDTO.name();
        this.email = registerRequestDTO.email();
        this.passwordHash = passwordHash;
        this.confirmationCode = confirmation_code;
        this.dateExpiration = date_expiration;
    }
}
