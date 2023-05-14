package com.example.composeweather.alertlist.io

import com.example.composeweather.alertlist.data.WeatherAlertsDataSource
import com.example.composeweather.entities.WeatherAlert
import com.example.composeweather.network.models.toWeatherAlertList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class RetrofitWeatherAlertsDataSource(
    private val api: WeatherAlertsAPI,
    private val workDispatcher: CoroutineDispatcher
) : WeatherAlertsDataSource {

    override suspend fun getAlerts(): List<WeatherAlert> {
        return withContext(workDispatcher) {
            api.getAlertsWrapper().toWeatherAlertList()
        }
    }
}