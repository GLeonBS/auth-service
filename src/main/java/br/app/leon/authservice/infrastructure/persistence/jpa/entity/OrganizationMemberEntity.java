package br.app.leon.authservice.infrastructure.persistence.jpa.entity;

import br.app.leon.authservice.domain.member.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "organization_members", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"organization_id", "user_id"})
})
@Data
@ToString(exclude = {"organization", "user"}) 
@EqualsAndHashCode(onlyExplicitlyIncluded = true) 
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationMemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include // ← Só o ID no equals/hashCode
    private UUID id;

    @ManyToOne()
    @JoinColumn(name = "organization_id", nullable = false, foreignKey = @ForeignKey(name = "fk_member_organization"))
    private OrganizationEntity organization;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_member_user"))
    private UserAccessEntity user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role;

    @Column(nullable = false)
    private boolean active;

    @CreationTimestamp
    @Column(name = "joined_at", nullable = false, updatable = false)
    private LocalDateTime joinedAt;
}