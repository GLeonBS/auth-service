package br.app.leon.authservice.infrastructure.persistence.jpa.repository;

import br.app.leon.authservice.infrastructure.persistence.jpa.entity.UserAccessEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface JpaUserAccessRepository extends JpaRepository<UserAccessEntity, UUID> {

    Optional<UserAccessEntity> findByEmail(String email);

    boolean existsByEmail(String email);
}