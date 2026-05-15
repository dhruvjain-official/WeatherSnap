package com.yourname.weathersnap.data

import com.yourname.weathersnap.data.local.WeatherReportDao
import com.yourname.weathersnap.data.local.WeatherReportEntity
import javax.inject.Inject

class ReportRepository @Inject constructor(
    private val dao: WeatherReportDao
) {

    suspend fun insertReport(
        report: WeatherReportEntity
    ) {

        dao.insertReport(report)
    }

    suspend fun getAllReports():
            List<WeatherReportEntity> {

        return dao.getAllReports()
    }
}