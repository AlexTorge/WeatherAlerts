package com.example.composeweather.alertlist.io

import com.example.composeweather.network.models.NetworkAlertsWrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAlertsAPI {
    @GET("alerts/active?status=actual&message_type=alert")
    suspend fun getAlertsWrapper(
        @Query("status") status: String = "actual",
        @Query("message_type") messageType: String = "alert"
    ): NetworkAlertsWrapper
}