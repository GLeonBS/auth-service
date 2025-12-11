package br.app.leon.authservice.domain.token;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository {

    RefreshToken save(RefreshToken token);

    Optional<RefreshToken> findById(UUID id);

    Optional<RefreshToken> findByToken(String token);

    List<RefreshToken> findByUserIdAndOrganizationIdAndRevokedFalse(UUID userId, UUID organizationId);

    List<RefreshToken> findByUserId(UUID userId);

    void deleteById(UUID id);

    void deleteExpiredTokens();
}