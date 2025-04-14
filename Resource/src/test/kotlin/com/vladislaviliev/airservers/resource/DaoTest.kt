package com.vladislaviliev.airservers.resource

import com.vladislaviliev.airservers.resource.reading.Reading
import com.vladislaviliev.airservers.resource.reading.Sensor
import com.vladislaviliev.airservers.resource.reading.Type
import com.vladislaviliev.airservers.resource.reading.dao.Dao
import jakarta.transaction.Transactional
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.time.Instant

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DaoTest {

    @Autowired
    private lateinit var dao: Dao

    @BeforeEach
    fun before() {
        val sensors = listOf(
            Sensor(0.0, 0.0, Type.PM10),
            Sensor(3.0, 1.0, Type.PM10),
            Sensor(-1.0, 1.0, Type.PM10),
            Sensor(-1.0, -10.0, Type.PM10),
        )
        val readings = sensors.map { Reading(it, 0.0, Instant.now()) }
        dao.saveAll(readings)
    }

    @AfterEach
    fun after() {
        dao.deleteAll()
    }

    @Test
    @Transactional
    fun testSquareCount() {
        val readings = dao.findReadings(0.0, 0.0, 10.0)
        Assertions.assertEquals(2, readings.count())
    }
}