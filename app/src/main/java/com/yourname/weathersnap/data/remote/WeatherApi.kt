package com.yourname.weathersnap.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import com.yourname.weathersnap.data.remote.WeatherResponse

interface WeatherApi {

    @GET("v1/forecast")
    suspend fun getWeather(

        @Query("latitude")
        latitude: Double,

        @Query("longitude")
        longitude: Double,

        @Query("current")
        current: String = "temperature_2m,relative_humidity_2m,wind_speed_10m,surface_pressure,weather_code"

    ): WeatherResponse

    @GET("https://geocoding-api.open-meteo.com/v1/search")
    suspend fun searchCity(

        @Query("name")
        city: String

    ): CityResponse
}
