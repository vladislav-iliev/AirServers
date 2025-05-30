package com.vladislaviliev.airservers.oauth.credentials.userAuthority;

import com.vladislaviliev.airservers.oauth.credentials.authority.PersistedAuthority;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
public class PersistedUserAuthority {

    @NonNull
    @NotNull
    @EmbeddedId
    private PersistedUserAuthorityId id;

    @ManyToOne
    @JoinColumn(name = PersistedUserAuthorityId_.AUTHORITY_ID, insertable = false, updatable = false)
    private PersistedAuthority authority;

    @CreationTimestamp
    private LocalDateTime createdOn;
}
