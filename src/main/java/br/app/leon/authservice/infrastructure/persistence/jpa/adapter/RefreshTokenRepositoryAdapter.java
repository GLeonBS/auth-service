package br.app.leon.authservice.infrastructure.persistence.jpa.adapter;

import br.app.leon.authservice.domain.organization.Organization;
import br.app.leon.authservice.domain.token.RefreshToken;
import br.app.leon.authservice.domain.token.RefreshTokenRepository;
import br.app.leon.authservice.domain.useraccess.UserAccess;
import br.app.leon.authservice.infrastructure.persistence.jpa.entity.OrganizationEntity;
import br.app.leon.authservice.infrastructure.persistence.jpa.entity.RefreshTokenEntity;
import br.app.leon.authservice.infrastructure.persistence.jpa.entity.UserAccessEntity;
import br.app.leon.authservice.infrastructure.persistence.jpa.repository.JpaRefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RefreshTokenRepositoryAdapter implements RefreshTokenRepository {
    
    private final JpaRefreshTokenRepository jpaRepository;
    
    @Override
    public RefreshToken save(RefreshToken token) {
        RefreshTokenEntity entity = toEntity(token);
        RefreshTokenEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }
    
    @Override
    public Optional<RefreshToken> findById(UUID id) {
        return jpaRepository.findById(id)
                .map(this::toDomain);
    }
    
    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return jpaRepository.findByToken(token)
                .map(this::toDomain);
    }
    
    @Override
    public List<RefreshToken> findByUserIdAndOrganizationIdAndRevokedFalse(UUID userId, UUID organizationId) {
        return jpaRepository.findByUserIdAndOrganizationIdAndRevokedFalse(userId, organizationId).stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<RefreshToken> findByUserId(UUID userId) {
        return jpaRepository.findByUserId(userId).stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }
    
    @Override
    @Transactional
    public void deleteExpiredTokens() {
        jpaRepository.deleteExpiredTokens(LocalDateTime.now());
    }

    private RefreshTokenEntity toEntity(RefreshToken domain) {

        Organization organizationDomain = domain.getOrganization();
        OrganizationEntity organization = new OrganizationEntity(organizationDomain.getId(), organizationDomain.getName(), organizationDomain.getSlug(), organizationDomain.isActive(), organizationDomain.getCreatedAt(), organizationDomain.getUpdatedAt());

        UserAccess userDomain = domain.getUser();
        UserAccessEntity user = new UserAccessEntity(userDomain.getId(), userDomain.getEmail(), userDomain.getPassword(), userDomain.getName(), userDomain.isActive(), userDomain.getCreatedAt(), userDomain.getUpdatedAt());
        
        return RefreshTokenEntity.builder()
                .id(domain.getId())
                .token(domain.getToken())
                .user(user)
                .organization(organization)
                .expiresAt(domain.getExpiresAt())
                .revoked(domain.isRevoked())
                .createdAt(domain.getCreatedAt())
                .build();
    }

    private RefreshToken toDomain(RefreshTokenEntity entity) {
        OrganizationEntity organizationEntity = entity.getOrganization();
        Organization organization = new Organization(organizationEntity.getId(), organizationEntity.getName(), organizationEntity.getSlug(), organizationEntity.isActive(), organizationEntity.getCreatedAt(), organizationEntity.getUpdatedAt());

        UserAccessEntity userEntity = entity.getUser();
        UserAccess user = new UserAccess(userEntity.getId(), userEntity.getEmail(), userEntity.getPassword(), userEntity.getName(), userEntity.isActive(), userEntity.getCreatedAt(), userEntity.getUpdatedAt());
        return RefreshToken.builder()
                .id(entity.getId())
                .token(entity.getToken())
                .user(user)
                .organization(organization)
                .expiresAt(entity.getExpiresAt())
                .revoked(entity.isRevoked())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}