package br.app.leon.authservice.domain.token;

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
    private UUID userId;
    private UUID organizationId;
    private LocalDateTime expiresAt;
    private boolean revoked;
    private LocalDateTime createdAt;
}