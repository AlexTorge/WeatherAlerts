package com.example.composeweather.alertlist.data

import com.example.composeweather.entities.WeatherAlert
import javax.inject.Inject

class WeatherAlertsRepository @Inject constructor(
    private val dataSource: WeatherAlertsDataSource
) {
    suspend fun getWeatherAlerts(): List<WeatherAlert> {
        return dataSource.getAlerts()
    }
}