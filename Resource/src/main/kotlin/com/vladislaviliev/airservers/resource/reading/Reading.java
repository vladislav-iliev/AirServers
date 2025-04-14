package com.vladislaviliev.airservers.resource.reading;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@Entity
@org.hibernate.annotations.Immutable
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
public class Reading {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @Embedded
    private Sensor sensor;

    @NonNull
    @NotNull
    @Column(nullable = false)
    private Double value;

    @NonNull
    @NotNull
    @Column(nullable = false)
    private Instant timestamp;
}
