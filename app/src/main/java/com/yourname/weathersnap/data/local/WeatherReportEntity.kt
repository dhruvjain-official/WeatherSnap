package com.yourname.weathersnap.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_reports")
data class WeatherReportEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val city: String,

    val temperature: Double,

    val humidity: Int,

    val windSpeed: Double,

    val pressure: Double,

    val notes: String,

    val imagePath: String,

    val compressedImagePath: String,

    val originalSizeKb: Int,

    val compressedSizeKb: Int,

    val createdAt: Long
)