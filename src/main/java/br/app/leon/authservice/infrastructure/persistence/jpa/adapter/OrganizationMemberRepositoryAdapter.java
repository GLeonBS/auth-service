package br.app.leon.authservice.infrastructure.persistence.jpa.adapter;

import br.app.leon.authservice.domain.member.OrganizationMember;
import br.app.leon.authservice.domain.member.OrganizationMemberRepository;
import br.app.leon.authservice.domain.organization.Organization;
import br.app.leon.authservice.domain.useraccess.UserAccess;
import br.app.leon.authservice.infrastructure.persistence.jpa.entity.OrganizationEntity;
import br.app.leon.authservice.infrastructure.persistence.jpa.entity.OrganizationMemberEntity;
import br.app.leon.authservice.infrastructure.persistence.jpa.entity.UserAccessEntity;
import br.app.leon.authservice.infrastructure.persistence.jpa.repository.JpaOrganizationMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrganizationMemberRepositoryAdapter implements OrganizationMemberRepository {
    
    private final JpaOrganizationMemberRepository jpaRepository;
    
    @Override
    public OrganizationMember save(OrganizationMember member) {
        OrganizationMemberEntity entity = toEntity(member);
        OrganizationMemberEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }
    
    @Override
    public Optional<OrganizationMember> findById(UUID id) {
        return jpaRepository.findById(id)
                .map(this::toDomain);
    }
    
    @Override
    public Optional<OrganizationMember> findByOrganizationIdAndUserId(UUID organizationId, UUID userId) {
        return jpaRepository.findByOrganizationIdAndUserId(organizationId, userId)
                .map(this::toDomain);
    }
    
    @Override
    public List<OrganizationMember> findByOrganizationId(UUID organizationId) {
        return jpaRepository.findByOrganizationId(organizationId).stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<OrganizationMember> findByUserId(UUID userId) {
        return jpaRepository.findByUserId(userId).stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public boolean existsByOrganizationIdAndUserId(UUID organizationId, UUID userId) {
        return jpaRepository.existsByOrganizationIdAndUserId(organizationId, userId);
    }
    
    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }

    private OrganizationMemberEntity toEntity(OrganizationMember domain) {
        Organization organizationDomain = domain.getOrganization();
        OrganizationEntity organization = new OrganizationEntity(organizationDomain.getId(), organizationDomain.getName(), organizationDomain.getSlug(), organizationDomain.isActive(), organizationDomain.getCreatedAt(), organizationDomain.getUpdatedAt());

        UserAccess userDomain = domain.getUser();
        UserAccessEntity user = new UserAccessEntity(userDomain.getId(), userDomain.getEmail(), userDomain.getPassword(), userDomain.getName(), userDomain.isActive(), userDomain.getCreatedAt(), userDomain.getUpdatedAt());
        
        return OrganizationMemberEntity.builder()
                .id(domain.getId())
                .organization(organization)
                .user(user)
                .role(domain.getRole())
                .active(domain.isActive())
                .joinedAt(domain.getJoinedAt())
                .build();
    }

    private OrganizationMember toDomain(OrganizationMemberEntity entity) {
        OrganizationEntity organizationEntity = entity.getOrganization();
        Organization organization = new Organization(organizationEntity.getId(), organizationEntity.getName(), organizationEntity.getSlug(), organizationEntity.isActive(), organizationEntity.getCreatedAt(), organizationEntity.getUpdatedAt());

        UserAccessEntity userEntity = entity.getUser();
        UserAccess user = new UserAccess(userEntity.getId(), userEntity.getEmail(), userEntity.getPassword(), userEntity.getName(), userEntity.isActive(), userEntity.getCreatedAt(), userEntity.getUpdatedAt());
        return OrganizationMember.builder()
                .id(entity.getId())
                .organization(organization)
                .user(user)
                .role(entity.getRole())
                .active(entity.isActive())
                .joinedAt(entity.getJoinedAt())
                .build();
    }
}