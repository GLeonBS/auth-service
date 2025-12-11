package br.app.leon.authservice.domain.token;

import br.app.leon.authservice.domain.organization.Organization;
import br.app.leon.authservice.domain.useraccess.UserAccess;
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
public class RefreshToken {
    private UUID id;
    private String token;
    private UserAccess user;
    private Organization organization;
    private LocalDateTime expiresAt;
    private boolean revoked;
    private LocalDateTime createdAt;
}