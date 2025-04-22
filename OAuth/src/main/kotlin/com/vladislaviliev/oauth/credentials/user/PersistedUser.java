package com.vladislaviliev.oauth.credentials.user;

import com.vladislaviliev.oauth.credentials.userAuthority.PersistedUserAuthority;
import com.vladislaviliev.oauth.credentials.userAuthority.PersistedUserAuthorityId_;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
public class PersistedUser {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @NotNull
    @Column(nullable = false)
    private String username;

    @NonNull
    @NotNull
    @Column(nullable = false)
    private String password;

    @NonNull
    @NotNull
    @Column(nullable = false)
    private Boolean enabled;

    @OneToMany
    @JoinColumn(name = PersistedUserAuthorityId_.USER_ID)
    private final Set<PersistedUserAuthority> authorities = new HashSet<>();
}
