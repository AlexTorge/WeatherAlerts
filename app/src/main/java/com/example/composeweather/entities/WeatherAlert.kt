package com.example.composeweather.entities

import java.time.LocalDateTime

data class WeatherAlert(
    val event: String,
    val senderName: String,
    val starts: LocalDateTime,
    val ends: LocalDateTime,
    val durationInMin: Long
)