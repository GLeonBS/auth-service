package br.app.leon.authservice.infrastructure.persistence.jpa.repository;

import br.app.leon.authservice.infrastructure.persistence.jpa.entity.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface JpaOrganizationRepository extends JpaRepository<OrganizationEntity, UUID> {

    Optional<OrganizationEntity> findBySlug(String slug);

    boolean existsBySlug(String slug);
}