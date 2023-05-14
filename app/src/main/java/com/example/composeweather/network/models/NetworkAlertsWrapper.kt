package com.example.composeweather.network.models

import com.example.composeweather.entities.WeatherAlert
import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoUnit

//Only necessary data was kept from the API models

@Serializable
data class NetworkAlertsWrapper(
    val features: ArrayList<NetworkFeature> = arrayListOf(),
)

@Serializable
data class NetworkFeature(
    val properties: NetworkProperties? = null
)

data class NetworkProperties(
    val effective: String? = null,
    val ends: String? = null,
    val event: String? = null,
    val senderName: String? = null,
)

fun NetworkAlertsWrapper.toWeatherAlertList() =
    this.features.filter { networkFeature ->
        networkFeature.properties != null && !networkFeature.properties.ends.isNullOrBlank() && !networkFeature.properties.effective.isNullOrBlank() && !networkFeature.properties.event.isNullOrBlank() && !networkFeature.properties.senderName.isNullOrBlank()
    }.map { networkFeature ->
        with(networkFeature.properties) {
            val formatter =
                DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd'T'HH:mm:ss").parseLenient()
                    .appendOffset("+HH:MM", "Z").toFormatter()
            val starts = LocalDateTime.parse(this!!.effective, formatter)
            val ends = LocalDateTime.parse(this.ends, formatter)
            WeatherAlert(
                event = this.event!!,
                senderName = this.senderName!!,
                starts = starts,
                ends = ends,
                durationInMin = ChronoUnit.MINUTES.between(starts, ends),
            )
        }
    }