package br.app.leon.authservice.domain.organization;

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
public class Organization {
    private UUID id;
    private String name;
    private String slug;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}