package com.yourname.weathersnap.presentation.report

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ReportsScreen(
    navController: NavController
) {

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
                            text = "Saved Reports",
                            fontSize = 18.sp,
                            color = Color(0xFF243010),
                            letterSpacing = 0.1.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Text(
                            text = "0 reports stored locally",
                            fontSize = 10.sp,
                            color = Color.DarkGray,
                            letterSpacing = 0.1.sp,
                            maxLines = 2,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Button(
                        onClick = {
                            navController.popBackStack()
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
                            text = "Back",
                            fontSize = 10.sp
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(18.dp),

            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF2C2B24)
            )
        ) {

            Column(
                modifier = Modifier.padding(18.dp)
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0xFF596300),
                                    Color(0xFF4B5121)
                                )
                            )
                        ),

                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = "No reports yet",
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    text = "Create and save a weather report to see image, notes, and weather details here.",

                    color = Color.LightGray,

                    fontSize = 14.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReportScreenPreview() {

}