package br.app.leon.authservice.domain.useraccess;

import java.util.Optional;
import java.util.UUID;

public interface UserAccessRepository {
    
    UserAccess save(UserAccess userAccess);

    Optional<UserAccess> findById(UUID id);

    Optional<UserAccess> findByEmail(String email);

    boolean existsByEmail(String email);

    void deleteById(UUID id);
}