package com.yourname.weathersnap.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [WeatherReportEntity::class],
    version = 2
)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun weatherReportDao():
            WeatherReportDao
}