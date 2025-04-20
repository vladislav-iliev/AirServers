package com.vladislaviliev.oauth.credentials.userAuthority;

import com.vladislaviliev.oauth.credentials.PersistedAuthority;
import com.vladislaviliev.oauth.credentials.PersistedUserAuthorityId_;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "User_Authority")
public class PersistedUserAuthority {

    @EmbeddedId
    private PersistedUserAuthorityId id;

    @ManyToOne
    @MapsId(PersistedUserAuthorityId_.AUTHORITY_ID)
    @JoinColumn(name = PersistedUserAuthorityId_.AUTHORITY_ID)
    private PersistedAuthority authority;

    @CreationTimestamp
    private LocalDateTime createdOn;
}
