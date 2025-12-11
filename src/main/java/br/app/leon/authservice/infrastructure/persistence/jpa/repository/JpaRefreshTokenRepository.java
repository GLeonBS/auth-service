package br.app.leon.authservice.infrastructure.persistence.jpa.repository;

import br.app.leon.authservice.infrastructure.persistence.jpa.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface JpaRefreshTokenRepository extends JpaRepository<RefreshTokenEntity, UUID> {

    Optional<RefreshTokenEntity> findByToken(String token);

    @Query("SELECT t FROM RefreshTokenEntity t " +
           "JOIN FETCH t.user " +
           "JOIN FETCH t.organization " +
           "WHERE t.token = :token")
    Optional<RefreshTokenEntity> findByTokenWithDetails(@Param("token") String token);

    List<RefreshTokenEntity> findByUserIdAndOrganizationIdAndRevokedFalse(UUID userId, UUID organizationId);

    List<RefreshTokenEntity> findByUserId(UUID userId);

    @Modifying
    @Query("DELETE FROM RefreshTokenEntity t WHERE t.expiresAt < :now")
    void deleteExpiredTokens(@Param("now") LocalDateTime now);
}