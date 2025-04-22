package com.vladislaviliev.oauth.credentials.authority;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
public class PersistedAuthority {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @NotNull
    @Column(nullable = false)
    private String name;
}
