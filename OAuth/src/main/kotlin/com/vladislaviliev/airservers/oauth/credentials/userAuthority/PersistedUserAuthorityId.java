package com.vladislaviliev.airservers.oauth.credentials.userAuthority;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Embeddable

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PersistedUserAuthorityId implements Serializable {

    @NonNull
    @NotNull
    private Long userId;

    @NonNull
    @NotNull
    private Long authorityId;
}
