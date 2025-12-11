package br.app.leon.authservice.infrastructure.persistence.jpa.adapter;

import br.app.leon.authservice.domain.organization.Organization;
import br.app.leon.authservice.domain.organization.OrganizationRepository;
import br.app.leon.authservice.infrastructure.persistence.jpa.entity.OrganizationEntity;
import br.app.leon.authservice.infrastructure.persistence.jpa.repository.JpaOrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrganizationRepositoryAdapter implements OrganizationRepository {
    
    private final JpaOrganizationRepository jpaRepository;
    
    @Override
    public Organization save(Organization organization) {
        OrganizationEntity entity = toEntity(organization);
        OrganizationEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }
    
    @Override
    public Optional<Organization> findById(UUID id) {
        return jpaRepository.findById(id)
                .map(this::toDomain);
    }
    
    @Override
    public Optional<Organization> findBySlug(String slug) {
        return jpaRepository.findBySlug(slug)
                .map(this::toDomain);
    }
    
    @Override
    public boolean existsBySlug(String slug) {
        return jpaRepository.existsBySlug(slug);
    }
    
    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }

    private OrganizationEntity toEntity(Organization domain) {
        return new OrganizationEntity(domain.getId(), domain.getName(), domain.getSlug(), domain.isActive(), domain.getCreatedAt(), domain.getUpdatedAt());
    }

    private Organization toDomain(OrganizationEntity entity) {
        return new Organization(entity.getId(), entity.getName(), entity.getSlug(), entity.isActive(), entity.getCreatedAt(), entity.getUpdatedAt());
    }
}