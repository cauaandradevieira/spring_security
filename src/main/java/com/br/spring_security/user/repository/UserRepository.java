package com.br.spring_security.user.repository;

import com.br.spring_security.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>
{
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);

    @Query("""
            SELECT DISTINCT u FROM User u
            JOIN FETCH u.roles r
            WHERE u.email = :email
            """)
    Optional<User> findByEmailWithRoles(@Param("email") String email);

    @Query(value = """
            SELECT DISTINCT u FROM User u
            JOIN FETCH u.roles r
            WHERE u.id = :userId AND
                  r.id = :roleId
            """)
    Optional<User> findByIdAndRole(@Param("userId") UUID id,
                                   @Param("roleId") Integer oldRoleId);

    @Query(value = """
            SELECT DISTINCT u FROM User u
            JOIN FETCH u.roles r
            WHERE u.id = :userId
            """)
    Optional<User> findByIdWithRoles(@Param("userId") UUID id);

    @Query(value = """
            SELECT EXISTS(
                SELECT 1 FROM user_role ur
                WHERE ur.user_id = :userId
                AND ur.role_id = :roleId)
            """, nativeQuery = true)
    Boolean existsUserAndRole(@Param("userId") UUID userId,
                              @Param("roleId") Integer roleId);

}
