package com.vladislaviliev.airservers.resource.reading;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Embeddable
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
public class Sensor {

    @NonNull
    @NotNull
    @Column(nullable = false)
    private Double lat;

    @NonNull
    @NotNull
    @Column(nullable = false)
    private Double lng;

    @NonNull
    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;
}
