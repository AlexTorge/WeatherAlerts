package com.example.composeweather

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.TimeZone

fun LocalDateTime.toPrettyFormat(): String {
    return this.format(
        DateTimeFormatter.ofPattern("HH:mm yyyy-MM-dd").withZone(TimeZone.getDefault().toZoneId())
    )
}

fun Long.toPrettyTime(): String {
    val stringBuilder = StringBuilder()
    if (this / 60 > 0) {
        stringBuilder.append("${this / 60} hours and ")
    }
    stringBuilder.append("${this % 60} min")
    return stringBuilder.toString()
}