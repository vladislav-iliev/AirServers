package com.vladislaviliev.airserver

import com.vladislaviliev.airserver.reading.Reading
import com.vladislaviliev.airserver.reading.Sensor
import com.vladislaviliev.airserver.reading.Type
import com.vladislaviliev.airserver.reading.dao.Dao
import jakarta.transaction.Transactional
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.Instant

@SpringBootTest
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
    fun testCount() {
        val readings = dao.findReadings(0.0, 0.0, 10.0)
        Assertions.assertEquals(2, readings.count())
    }
}