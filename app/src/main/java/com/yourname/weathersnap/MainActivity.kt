package com.yourname.weathersnap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.yourname.weathersnap.ui.theme.WeatherSnapTheme
import com.yourname.weathersnap.presentation.weather.WeatherScreen
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yourname.weathersnap.presentation.report.ReportsScreen
import com.yourname.weathersnap.presentation.report.CreateReportScreen
import com.yourname.weathersnap.presentation.camera.CameraScreen
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween


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

                    composable(

                        route = "weather",

                        enterTransition = {

                            slideIntoContainer(

                                AnimatedContentTransitionScope.SlideDirection.Left,

                                animationSpec = tween(300)
                            )
                        },

                        exitTransition = {

                            slideOutOfContainer(

                                AnimatedContentTransitionScope.SlideDirection.Left,

                                animationSpec = tween(300)
                            )
                        },

                        popEnterTransition = {

                            slideIntoContainer(

                                AnimatedContentTransitionScope.SlideDirection.Right,

                                animationSpec = tween(300)
                            )
                        },

                        popExitTransition = {

                            slideOutOfContainer(

                                AnimatedContentTransitionScope.SlideDirection.Right,

                                animationSpec = tween(300)
                            )
                        }
                    ) {

                        WeatherScreen(navController)
                    }

                    composable("reports") {

                        ReportsScreen(navController)
                    }

                    composable(
                        route = "create_report?imagePath={imagePath}"
                    ) { backStackEntry ->

                        val imagePath =
                            backStackEntry.arguments
                                ?.getString("imagePath") ?: ""

                        CreateReportScreen(
                            navController = navController,
                            imagePath = imagePath
                        )
                    }

                    composable("camera_screen") {

                        CameraScreen(navController)
                    }
                }
            }
        }
    }
}

