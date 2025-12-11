package br.app.leon.authservice.domain.member;

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
    private UUID organizationId;
    private UUID userId;
    private Role role;
    private boolean active;
    private LocalDateTime joinedAt;
}