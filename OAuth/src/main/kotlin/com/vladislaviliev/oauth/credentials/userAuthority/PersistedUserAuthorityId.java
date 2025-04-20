package com.vladislaviliev.oauth.credentials.userAuthority;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PersistedUserAuthorityId implements Serializable {

    @NotNull
    private Long userId;

    @NotNull
    private Long authorityId;
}
