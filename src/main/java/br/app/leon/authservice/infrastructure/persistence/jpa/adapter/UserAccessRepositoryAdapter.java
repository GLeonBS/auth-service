package br.app.leon.authservice.infrastructure.persistence.jpa.adapter;

import br.app.leon.authservice.domain.useraccess.UserAccess;
import br.app.leon.authservice.domain.useraccess.UserAccessRepository;
import br.app.leon.authservice.infrastructure.persistence.jpa.entity.UserAccessEntity;
import br.app.leon.authservice.infrastructure.persistence.jpa.repository.JpaUserAccessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserAccessRepositoryAdapter implements UserAccessRepository {
    
    private final JpaUserAccessRepository jpaRepository;
    
    @Override
    public UserAccess save(UserAccess userAccess) {
        UserAccessEntity entity = toEntity(userAccess);
        UserAccessEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }
    
    @Override
    public Optional<UserAccess> findById(UUID id) {
        return jpaRepository.findById(id)
                .map(this::toDomain);
    }
    
    @Override
    public Optional<UserAccess> findByEmail(String email) {
        return jpaRepository.findByEmail(email)
                .map(this::toDomain);
    }
    
    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }
    
    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }

    private UserAccessEntity toEntity(UserAccess domain) {
        return new UserAccessEntity(domain.getId(), domain.getEmail(), domain.getPassword(), domain.getName(), domain.isActive(), domain.getCreatedAt(), domain.getUpdatedAt());
    }

    private UserAccess toDomain(UserAccessEntity entity) {
        return new UserAccess(entity.getId(), entity.getEmail(), entity.getPassword(), entity.getName(), entity.isActive(), entity.getCreatedAt(), entity.getUpdatedAt());
    }
}