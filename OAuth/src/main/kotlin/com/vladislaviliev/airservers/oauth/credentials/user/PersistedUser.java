package com.vladislaviliev.airservers.oauth.credentials.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class PersistedUser {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @NotNull
    @Column(nullable = false, unique = true)
    private String username;

    @NonNull
    @NotNull
    @Column(nullable = false)
    private String password;
}
