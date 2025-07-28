package com.br.spring_security.auth.repository;

import com.br.spring_security.auth.entity.PendingUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface PendingUserRepository extends JpaRepository<PendingUser, Integer>
{
    @Modifying
    @Transactional
    @Query("""
            DELETE FROM PendingUser p
            WHERE p.email = :email
            """)
    void deleteAllByEmail(@Param("email") String email);

    @Query("""
            SELECT p FROM PendingUser p
            WHERE p.email = :email
            AND p.confirmationCode = :code
            AND p.dateExpiration > CURRENT_TIMESTAMP
            """)
    Optional<PendingUser> findByEmailAndCode(@Param("email") String email,
                                             @Param("code") String code);
}
