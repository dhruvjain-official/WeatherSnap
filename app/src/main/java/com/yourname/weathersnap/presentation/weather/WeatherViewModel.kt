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
import com.yourname.weathersnap.data.remote.CityResult

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    var weatherData by mutableStateOf<WeatherResponse?>(null)
        private set

    var citySuggestions by mutableStateOf<List<CityResult>?>(emptyList())
        private set

    var isSearchingCities by mutableStateOf(false)
        private set

    var isLoadingWeather by mutableStateOf(false)
        private set

    fun getWeather(city: String) {

        viewModelScope.launch {
            isLoadingWeather = true
            try {

                val cityResult =
                    repository.searchCity(city)

                val location =
                    cityResult.results.first()

                weatherData = repository.getWeather(
                    latitude = location.latitude,
                    longitude = location.longitude
                )
                isLoadingWeather = false


            } catch (e: Exception) {
                isLoadingWeather = false
                println(e.message)
                e.printStackTrace()
            }
        }
    }
    fun searchCities(city: String) {

        viewModelScope.launch {

            try {

                if (city.length > 2) {
                    isSearchingCities = true
                    citySuggestions =
                        repository.searchCity(city).results
                    isSearchingCities = false

                } else {
                    isSearchingCities = false
                    citySuggestions = emptyList()
                }

            } catch (e: Exception) {
                isSearchingCities = false
                e.printStackTrace()
            }
        }
    }

    fun clearSuggestions() {

        citySuggestions = emptyList()
    }
}