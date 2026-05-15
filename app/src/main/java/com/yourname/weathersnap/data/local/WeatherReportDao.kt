package com.yourname.weathersnap.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeatherReportDao {

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insertReport(
        report: WeatherReportEntity
    )

    @Query(
        "SELECT * FROM weather_reports ORDER BY createdAt DESC"
    )
    suspend fun getAllReports(): List<WeatherReportEntity>
}