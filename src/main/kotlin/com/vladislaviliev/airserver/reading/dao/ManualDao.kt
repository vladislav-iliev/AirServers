package com.vladislaviliev.airserver.reading.dao

import com.vladislaviliev.airserver.reading.Reading
import java.util.stream.Stream

interface ManualDao {
    fun findReadings(lat: Double, lng: Double, side: Double): Stream<Reading>
}