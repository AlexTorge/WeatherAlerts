package com.example.composeweather.alertlist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeweather.alertlist.data.WeatherAlertsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherAlertsViewModel @Inject constructor(
    private val repository: WeatherAlertsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(WeatherAlertsUiState())
    val uiState: StateFlow<WeatherAlertsUiState> = _uiState.asStateFlow()

    init {
        getAlerts()
    }

    private fun getAlerts() {
        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val alerts = repository.getWeatherAlerts()
            _uiState.update { it.copy(isLoading = false, alerts = alerts) }
        }
    }
}