package br.app.leon.authservice.infrastructure.persistence.jpa.repository;

import br.app.leon.authservice.infrastructure.persistence.jpa.entity.OrganizationMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface JpaOrganizationMemberRepository extends JpaRepository<OrganizationMemberEntity, UUID> {
    

    Optional<OrganizationMemberEntity> findByOrganizationIdAndUserId(UUID organizationId, UUID userId);

    List<OrganizationMemberEntity> findByOrganizationId(UUID organizationId);

    List<OrganizationMemberEntity> findByUserId(UUID userId);

    @Query("SELECT m FROM OrganizationMemberEntity m " +
           "JOIN FETCH m.organization " +
           "JOIN FETCH m.user " +
           "WHERE m.organization.id = :organizationId")
    List<OrganizationMemberEntity> findByOrganizationIdWithDetails(@Param("organizationId") UUID organizationId);

    @Query("SELECT m FROM OrganizationMemberEntity m " +
           "JOIN FETCH m.organization " +
           "WHERE m.user.id = :userId")
    List<OrganizationMemberEntity> findByUserIdWithOrganization(@Param("userId") UUID userId);

    boolean existsByOrganizationIdAndUserId(UUID organizationId, UUID userId);
}