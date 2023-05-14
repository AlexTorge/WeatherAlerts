package com.example.composeweather.alertlist.ui

import com.example.composeweather.entities.WeatherAlert

data class WeatherAlertsUiState(
    val isLoading: Boolean = false, val alerts: List<WeatherAlert> = emptyList()
)