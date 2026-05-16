package com.yourname.weathersnap.presentation.weather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.sp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.material3.CircularProgressIndicator
import androidx.navigation.NavController
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.LocalTextStyle
import androidx.compose.foundation.border
import androidx.compose.material3.HorizontalDivider
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.offset

@Composable
fun WeatherScreen(
    navController: NavController,
    viewModel: WeatherViewModel = hiltViewModel()
){

    var city by remember {
        mutableStateOf("")
    }



    Column(

        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF2C310D),
                        Color(0xFF0C100C)
                    )
                )
            )
            .padding(horizontal = 16.dp)
            .padding(top = 48.dp)
            .verticalScroll(rememberScrollState()),

        verticalArrangement = Arrangement.Top,

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val weather = viewModel.weatherData
        val suggestions = viewModel.citySuggestions ?: emptyList()
        val isSearching = viewModel.isSearchingCities
        val isLoadingWeather = viewModel.isLoadingWeather
        val errorMessage = viewModel.errorMessage
        val selectedCity = viewModel.selectedCity

        val weatherCondition =
            if (weather != null) {

                when (
                    weather.current.weather_code
                ) {

                    0 -> "Clear sky"

                    1, 2, 3 ->
                        "Partly cloudy"

                    45, 48 ->
                        "Foggy"

                    51, 53, 55 ->
                        "Drizzle"

                    61, 63, 65 ->
                        "Rainy"

                    71, 73, 75 ->
                        "Snowfall"

                    95 ->
                        "Thunderstorm"

                    else ->
                        "Unknown weather"
                }

            } else {
                ""
            }


        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(7.dp),

            ) {
            Box(
                modifier = Modifier
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFFBFCC7E),
                                Color(0xFF9ECFC0)
                            )
                        )
                    )
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(7.dp))
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),

                    horizontalArrangement = Arrangement.SpaceBetween,

                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {

                        Text(
                            text = "WeatherSnap",
                            fontSize = 18.sp,
                            color = Color(0xFF243010),
                            letterSpacing = 0.1.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Text(
                            text = "Live weather reports with camera evidence",
                            fontSize = 10.sp,
                            color = Color.DarkGray,
                            letterSpacing = 0.1.sp,
                            maxLines = 2,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Button(
                        onClick = {
                            navController.navigate("reports")
                        },

                        modifier = Modifier.width(75.dp),

                        shape = RoundedCornerShape(14.dp),

                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF2A3402)
                        ),

                        contentPadding = PaddingValues(
                            horizontal = 14.dp,
                            vertical = 2.dp
                        )
                    ){
                        Text(
                            text = "Reports",
                            fontSize = 10.sp,
                            color = Color(0xFFC0CD7F)
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(7.dp),

            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF11150F)
            )
        ) {

            Column(
                modifier = Modifier.padding(14.dp)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        singleLine = true,

                        textStyle = LocalTextStyle.current.copy(
                            fontSize = 16.sp
                        ),



                        value = city,

                        onValueChange = {

                            city = it

                            viewModel.searchCities(it)
                        },

                        modifier = Modifier
                            .weight(1f)
                            .heightIn(min = 56.dp),

                        label = {
                            Text(
                                text = "City",
                                fontSize = 12.sp
                            )
                        },
                        shape = RoundedCornerShape(5.dp),

                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF8FA86E),
                            unfocusedBorderColor = Color.White,

                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,

                            focusedContainerColor = Color(0xFF1B2118),
                            unfocusedContainerColor = Color(0xFF1B2118),

                            focusedLabelColor = Color(0xFFB7E07A),
                            unfocusedLabelColor = Color.Gray,
                            cursorColor = Color(0xFFB7E07A),
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {

                            if (city.isNotBlank()) {

                                viewModel.getWeather(city)
                            }
                        },
                        shape = RoundedCornerShape(22.dp),

                        colors = ButtonDefaults.buttonColors(
                            containerColor =
                                if (isLoadingWeather)
                                    Color(0xFF5B5A4D)
                                else
                                    Color(0xFFC0CD7F)
                        ),

                        contentPadding = PaddingValues(
                            horizontal = 25.dp,
                            vertical = 2.dp
                        ),
                        modifier = Modifier
                            .width(95.dp)
                            .align(Alignment.CenterVertically)
                    ) {

                        if (isLoadingWeather) {

                            Text(
                                text = "...",
                                fontSize = 10.sp
                            )

                        } else {

                            Text(
                                text = "Search",
                                fontSize = 10.sp,
                                color = Color(0xFF2E3401)
                            )
                        }
                    }
                }

                Text(
                    text = "Enter more than 2 letters to start city suggestions.",
                    color = Color.Gray,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )


            }
        }


        AnimatedVisibility(

            visible =
                isSearching ||
                        suggestions.isNotEmpty(),

            enter =
                fadeIn() + expandVertically(),

            exit =
                fadeOut() + shrinkVertically()
        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),

                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF2C2B24)
                ),

                shape = RoundedCornerShape(7.dp)
            ) {

                Column(
                    modifier = Modifier.padding(vertical = 10.dp)
                ) {
                    if (isSearching) {

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(
                                top = 10.dp,
                                start = 10.dp,
                                end = 10.dp
                            )
                        ) {

                            CircularProgressIndicator(
                                modifier = Modifier.size(16.dp),
                                strokeWidth = 2.dp,
                                color = Color(0xFFB7E07A)
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            Text(
                                text = "Finding cities...",
                                color = Color.White,
                                fontSize = 13.sp
                            )
                        }
                    }

                    suggestions.orEmpty().take(5).forEachIndexed { index, suggestion ->

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    top = 6.dp,
                                    start = 10.dp,
                                    end = 10.dp
                                )
                                .border(
                                    width = 1.dp,
                                    color = Color(0xFF5B5A4D),
                                    shape = RoundedCornerShape(20.dp)
                                ),

                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFF2C2B24)
                            ),

                            shape = RoundedCornerShape(20.dp)
                        ) {

                            Text(
                                text = "${suggestion.name}, ${suggestion.country}",

                                color = Color.White,

                                fontSize = 13.sp,

                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {

                                        city =
                                            "${suggestion.name}, ${suggestion.country}"

                                        viewModel.clearSuggestions()
                                    }
                                    .padding(vertical = 10.dp),


                                textAlign = TextAlign.Center
                            )
                        }
                        if (index != suggestions.take(5).lastIndex) {

                            Spacer(modifier = Modifier.height(10.dp))

                            HorizontalDivider(
                                modifier = Modifier
                                    .fillMaxWidth(),

                                color = Color(0xFF5B5A4D),

                                thickness = 0.7.dp
                            )

                            Spacer(modifier = Modifier.height(3.dp))
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))

        if (errorMessage != null) {

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),

                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF3B1F1F)
                ),

                shape = RoundedCornerShape(10.dp)
            ) {

                Column(
                    modifier = Modifier.padding(16.dp),

                    horizontalAlignment =
                        Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Weather failed to load",

                        color = Color.White,

                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(6.dp)
                    )

                    Text(
                        text = errorMessage,

                        color = Color.LightGray,

                        fontSize = 13.sp
                    )

                    Spacer(
                        modifier = Modifier.height(14.dp)
                    )

                    Button(
                        onClick = {
                            viewModel.getWeather(city)
                        },

                        colors =
                            ButtonDefaults.buttonColors(
                                containerColor =
                                    Color(0xFFB7E07A)
                            )
                    ) {

                        Text(
                            text = "Retry",
                            color = Color.Black
                        )
                    }
                }
            }
        }

        else if (weather == null) {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(7.dp),

                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF2C2B24)
                )
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        Color(0xFF45492F),
                                        Color(0xFF21483E)
                                    )
                                )
                            ),

                        contentAlignment = Alignment.Center
                    ) {

                        Text(
                            text = "Search. Capture. Save.",
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 15.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "No weather loaded",
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp
                    )

                    Spacer(modifier = Modifier.height(3.dp))

                    Text(
                        text = "Enter more than 2 letters, choose a city, then search.",

                        color = Color.White,
                        fontSize = 11.sp
                    )
                }
            }

        }

        else {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(7.dp),

                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF11150F)
                )
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        text = selectedCity,
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "${weather.current.temperature_2m}°C",
                        color = Color(0xFFB7E07A),
                        fontSize = 42.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = weatherCondition,
                        color = Color.LightGray
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = "Humidity\n${weather.current.relative_humidity_2m}%",
                            color = Color.White
                        )

                        Text(
                            text =
                                "Wind\n${String.format("%.1f", weather.current.wind_speed_10m / 3.6)} m/s",
                            color = Color.White
                        )

                        Text(
                            text = "Pressure\n${weather.current.surface_pressure} hPa",
                            color = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {
                            navController.currentBackStackEntry
                                ?.savedStateHandle
                                ?.set("city", selectedCity)

                            navController.currentBackStackEntry
                                ?.savedStateHandle
                                ?.set(
                                    "temperature",
                                    weather.current.temperature_2m
                                )

                            navController.currentBackStackEntry
                                ?.savedStateHandle
                                ?.set(
                                    "humidity",
                                    weather.current.relative_humidity_2m
                                )

                            navController.currentBackStackEntry
                                ?.savedStateHandle
                                ?.set(
                                    "wind",
                                    weather.current.wind_speed_10m
                                )

                            navController.currentBackStackEntry
                                ?.savedStateHandle
                                ?.set(
                                    "pressure",
                                    weather.current.surface_pressure
                                )

                            navController.currentBackStackEntry
                                ?.savedStateHandle
                                ?.set(
                                    "weatherCode",
                                    weather.current.weather_code
                                )

                            navController.navigate("create_report")
                        },

                        modifier = Modifier.fillMaxWidth(),

                        shape = RoundedCornerShape(14.dp),

                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF8EA56C)
                        )
                    ) {

                        Text(
                            text = "Create Report",
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}
