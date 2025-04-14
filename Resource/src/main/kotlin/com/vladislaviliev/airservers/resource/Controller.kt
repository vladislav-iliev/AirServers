package com.vladislaviliev.airservers.resource

import com.vladislaviliev.airservers.resource.reading.Reading
import com.vladislaviliev.airservers.resource.reading.dao.Dao
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping
class Controller(private val dao: Dao) {

    @GetMapping
    fun findAll() = dao.findAll()

    // Search a square: top left coordinates + side
    @GetMapping("square")
    fun searchSquare(
        @RequestParam lat: Double,
        @RequestParam lng: Double,
        @RequestParam side: Double
    ) = dao.findReadings(lat, lng, side)

    @PostMapping
    fun post(@RequestBody readings: Iterable<Reading>) = dao.saveAll(readings)
}