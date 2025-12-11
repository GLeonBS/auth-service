package br.app.leon.authservice.domain.member;

import br.app.leon.authservice.domain.organization.Organization;
import br.app.leon.authservice.domain.useraccess.UserAccess;
import br.app.leon.authservice.infrastructure.persistence.jpa.entity.OrganizationEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationMember {
    private UUID id;
    private Organization organization;
    private UserAccess user;
    private Role role;
    private boolean active;
    private LocalDateTime joinedAt;
}