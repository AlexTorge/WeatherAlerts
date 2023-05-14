package com.example.composeweather.alertlist.data

import com.example.composeweather.entities.WeatherAlert

interface WeatherAlertsDataSource {
    suspend fun getAlerts(): List<WeatherAlert>
}