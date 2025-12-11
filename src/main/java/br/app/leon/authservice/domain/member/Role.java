package br.app.leon.authservice.domain.member;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum Role {
    OWNER("Proprietário"),
    ADMIN("Administrador"),
    MEMBER("Membro");

    private final String displayName;

}