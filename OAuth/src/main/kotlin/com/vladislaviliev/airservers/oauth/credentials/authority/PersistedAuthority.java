package com.vladislaviliev.airservers.oauth.credentials.authority;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
public class PersistedAuthority {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @NotNull
    @Column(nullable = false, unique = true)
    private String name;
}
