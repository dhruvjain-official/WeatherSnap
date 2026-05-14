package com.yourname.weathersnap.navigation

sealed class Routes(val route: String) {

    data object WeatherScreen : Routes("weather")

    data object CreateReportScreen : Routes("create_report")

    data object CameraScreen : Routes("camera")

    data object SavedReportsScreen : Routes("saved_reports")
}