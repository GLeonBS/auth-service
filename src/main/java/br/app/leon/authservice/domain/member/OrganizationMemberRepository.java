package br.app.leon.authservice.domain.member;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface OrganizationMemberRepository {


    OrganizationMember save(OrganizationMember member);

    Optional<OrganizationMember> findById(UUID id);

    Optional<OrganizationMember> findByOrganizationIdAndUserId(UUID organizationId, UUID userId);

    List<OrganizationMember> findByOrganizationId(UUID organizationId);

    List<OrganizationMember> findByUserId(UUID userId);

    boolean existsByOrganizationIdAndUserId(UUID organizationId, UUID userId);

    void deleteById(UUID id);
}