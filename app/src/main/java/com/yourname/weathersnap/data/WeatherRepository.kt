package com.yourname.weathersnap.data

import com.yourname.weathersnap.data.remote.WeatherApi
import javax.inject.Inject
import com.yourname.weathersnap.data.remote.WeatherResponse

class WeatherRepository @Inject constructor(
    private val weatherApi: WeatherApi
) {

    suspend fun getWeather(
        latitude: Double,
        longitude: Double
    ): WeatherResponse {

        return weatherApi.getWeather(
            latitude = latitude,
            longitude = longitude
        )
    }

    suspend fun searchCity(
        city: String
    ) = weatherApi.searchCity(city)
}