package com.vladislaviliev.airservers.oauth.credentials.salt;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
public class Salt {

    public static final String USER_PASSWORD = "user_password";

    @Id
    @NonNull
    private String id;

    @NonNull
    @NotNull
    @Column(nullable = false)
    private String valuation;

    @NonNull
    @NotNull
    @Column(nullable = false)
    private String info;

}
