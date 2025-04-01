package com.vladislaviliev.airserver

import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun provideGeneralInfo(e: Any) = ResponseEntity.badRequest().body(
        "POST like: \n" + """
            [
              {
                "id": null,
                "sensor": {
                  "lat": 51.5074,
                  "lng": -0.1278,
                  "type": "TEMP"
                },
                "value": 23.5,
                "timestamp": "2023-12-01T12:00:00Z"
              },
              {
                "id": null,
                "sensor": {
                  "lat": 48.8566,
                  "lng": 2.3522,
                  "type": "HUMID"
                },
                "value": 65.2,
                "timestamp": "2023-12-01T12:15:00Z"
              }
            ]

        """.trimIndent()
    )
}