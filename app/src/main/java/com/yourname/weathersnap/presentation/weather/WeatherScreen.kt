package com.yourname.weathersnap.presentation.weather

import androidx.compose.ui.tooling.preview.Preview

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
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun WeatherScreen(
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
                        Color(0xFF2A300B),
                        Color(0xFF111813)
                    )
                )
            )
            .padding(horizontal = 16.dp)
            .padding(top = 48.dp),

        verticalArrangement = Arrangement.Top,

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val weather = viewModel.weatherData

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

                        },
                        shape = RoundedCornerShape(9.dp),

                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF2A3402)
                        ),
                        contentPadding = PaddingValues(
                            horizontal = 14.dp,
                            vertical = 2.dp
                        )

                    ) {

                        Text(
                            text = "Reports",
                            fontSize = 10.sp
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),

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
                        value = city,

                        onValueChange = {
                            city = it
                        },

                        modifier = Modifier.weight(1f),

                        label = {
                            Text(text = "City")
                        },
                        shape = RoundedCornerShape(5.dp),

                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF8FA86E),
                            unfocusedBorderColor = Color(0xFF4A5440),

                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,

                            focusedContainerColor = Color(0xFF1B2118),
                            unfocusedContainerColor = Color(0xFF1B2118)
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {
                            viewModel.getWeather()
                        },
                        shape = RoundedCornerShape(22.dp),

                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF8EA56C)
                        ),

                        contentPadding = PaddingValues(
                            horizontal = 25.dp,
                            vertical = 2.dp
                        ),
                        modifier = Modifier.align(Alignment.CenterVertically)
                    ) {

                        Text(
                            text = "Search",
                            fontSize = 10.sp
                        )
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
        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),

            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF11150F)
            )
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = "Jaipur",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "${weather?.current?.temperature_2m ?: "--"}°C",
                    color = Color(0xFFB7E07A),
                    fontSize = 42.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Cloudy",
                    color = Color.LightGray
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = "Humidity\n${weather?.current?.relative_humidity_2m ?: "--"}%",
                        color = Color.White
                    )

                    Text(
                        text = "Wind\n${weather?.current?.wind_speed_10m ?: "--"} km/h",
                        color = Color.White
                    )

                    Text(
                        text = "Pressure\n${weather?.current?.surface_pressure ?: "--"} hPa",
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherScreenPreview() {

}