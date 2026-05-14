package com.yourname.weathersnap.data.remote

data class CityResponse(

    val results: List<CityResult>
)

data class CityResult(

    val name: String,

    val latitude: Double,

    val longitude: Double,

    val country: String
)