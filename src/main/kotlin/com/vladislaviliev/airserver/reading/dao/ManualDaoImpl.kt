package com.vladislaviliev.airserver.reading.dao

import com.vladislaviliev.airserver.reading.Reading
import com.vladislaviliev.airserver.reading.Reading_
import com.vladislaviliev.airserver.reading.Sensor
import com.vladislaviliev.airserver.reading.Sensor_
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import java.util.stream.Stream

class ManualDaoImpl(
    @PersistenceContext private val entityManager: EntityManager,
) : ManualDao {

    override fun findReadings(
        lat: Double,
        lng: Double,
        side: Double
    ): Stream<Reading> {

        val cb = entityManager.criteriaBuilder
        val cq = cb.createQuery(Reading::class.java)
        val root = cq.from(Reading::class.java)

        // Create predicates for filtering
        val sensor = root.get<Sensor>(Reading_.SENSOR)
        val latPredicate = cb.between(sensor.get(Sensor_.LAT), lat - side, lat)
        val lngPredicate = cb.between(sensor.get(Sensor_.LNG), lng, lng + side)

        // Apply predicates to the query
        cq.where(cb.and(latPredicate, lngPredicate))

        // Execute the query
        return entityManager.createQuery(cq).resultStream
    }
}