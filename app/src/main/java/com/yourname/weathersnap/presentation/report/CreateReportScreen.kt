package com.yourname.weathersnap.presentation.report

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun CreateReportScreen(
    navController: NavController
) {

    var notes by remember {
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

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
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
                    .clip(RoundedCornerShape(8.dp))
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),

                    horizontalArrangement = Arrangement.SpaceBetween,

                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column {

                        Text(
                            text = "Create Report",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF243010)
                        )

                        Text(
                            text = "Capture weather evidence",

                            fontSize = 11.sp,

                            color = Color.DarkGray
                        )
                    }

                    Button(
                        onClick = {
                            navController.popBackStack()
                        },

                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF2A3402)
                        ),

                        shape = RoundedCornerShape(10.dp)
                    ) {

                        Text(
                            text = "Back"
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),

            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF11150F)
            ),

            shape = RoundedCornerShape(10.dp)
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = "Weather Snapshot",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Temperature: --°C",
                    color = Color.White
                )

                Text(
                    text = "Humidity: --%",
                    color = Color.White
                )

                Text(
                    text = "Wind: -- km/h",
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),

            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF1B2118)
            ),

            shape = RoundedCornerShape(10.dp)
        ) {

            Box(
                modifier = Modifier.fillMaxSize(),

                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "No Image Captured",
                    color = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = notes,

            onValueChange = {
                notes = it
            },

            modifier = Modifier.fillMaxWidth(),

            label = {
                Text("Weather Notes")
            },

            shape = RoundedCornerShape(10.dp),

            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF8FA86E),
                unfocusedBorderColor = Color(0xFF4A5440),

                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,

                focusedContainerColor = Color(0xFF1B2118),
                unfocusedContainerColor = Color(0xFF1B2118)
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { },

            modifier = Modifier.fillMaxWidth(),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF8EA56C)
            ),

            shape = RoundedCornerShape(14.dp)
        ) {

            Text(
                text = "Capture Weather Photo"
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { },

            modifier = Modifier.fillMaxWidth(),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2D6A4F)
            ),

            shape = RoundedCornerShape(14.dp)
        ) {

            Text(
                text = "Save Report"
            )
        }
    }
}