package com.vladislaviliev.airservers.resource.reading.dao

import com.vladislaviliev.airservers.resource.reading.Reading
import java.util.stream.Stream

interface ManualDao {
    fun findReadings(lat: Double, lng: Double, side: Double): Stream<Reading>
}