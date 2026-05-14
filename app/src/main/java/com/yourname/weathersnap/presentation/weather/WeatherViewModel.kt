package com.yourname.weathersnap.presentation.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yourname.weathersnap.data.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.yourname.weathersnap.data.remote.WeatherResponse

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    var weatherData by mutableStateOf<WeatherResponse?>(null)
        private set
    fun getWeather() {

        viewModelScope.launch {

            weatherData = repository.getWeather(
                latitude = 26.9124,
                longitude = 75.7873
            )
        }
    }
}