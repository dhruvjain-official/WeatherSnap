package com.yourname.weathersnap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.yourname.weathersnap.ui.theme.WeatherSnapTheme
import com.yourname.weathersnap.presentation.weather.WeatherScreen
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yourname.weathersnap.presentation.report.ReportsScreen
import com.yourname.weathersnap.presentation.report.CreateReportScreen
import com.yourname.weathersnap.presentation.camera.CameraScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherSnapTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "weather"
                ) {

                    composable("weather") {

                        WeatherScreen(navController)
                    }

                    composable("reports") {

                        ReportsScreen(navController)
                    }

                    composable("create_report") {

                        CreateReportScreen(navController)
                    }

                    composable("camera_screen") {

                        CameraScreen(navController)
                    }
                }
            }
        }
    }
}

