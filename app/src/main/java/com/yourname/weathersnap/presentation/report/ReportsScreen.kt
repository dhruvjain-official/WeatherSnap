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
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.layout.ContentScale
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.runtime.remember

@Composable
fun ReportsScreen(
    navController: NavController,
    viewModel: ReportViewModel = hiltViewModel()
) {

    val reports = viewModel.reports

    Column(

        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF1A2B22),
                        Color(0xFF111109)
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
                                Color(0xFFA3CFC1),
                                Color(0xFFBFCD80)
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
                            color = Color(0xFF1A2B22),
                            letterSpacing = 0.1.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Text(
                            text = "${reports.size} report stored locally",
                            fontSize = 10.sp,
                            color = Color(0xFF1A2B22),
                            letterSpacing = 0.1.sp,
                            maxLines = 2,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Button(
                        onClick = {
                            navController.navigate("weather") {

                                popUpTo("weather") {
                                    inclusive = true
                                }

                                launchSingleTop = true
                            }
                        },

                        modifier = Modifier.width(75.dp),

                        shape = RoundedCornerShape(20.dp),

                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF1A2B22)
                        ),

                        contentPadding = PaddingValues(
                            horizontal = 14.dp,
                            vertical = 2.dp
                        )
                    ){

                        Text(
                            text = "Back",
                            fontSize = 10.sp
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (reports.isEmpty()) {

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
                                        Color(0xFF434B08),
                                        Color(0xFF959F3A)
                                    )
                                )
                            ),

                        contentAlignment = Alignment.Center
                    ) {

                        Text(
                            text = "No reports yet",
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    Text(
                        text = "Create and save a weather report to see image, notes, and weather details here.",

                        color = Color.LightGray,

                        fontSize = 10.sp
                    )
                }
            }

        } else {

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {

                items(reports) { report ->
                    val configuration =
                        LocalConfiguration.current

                    val formattedDate = remember(
                        report.createdAt,
                        configuration
                    ) {

                        SimpleDateFormat(
                            "dd MMM yyyy, hh:mm a",
                            configuration.locales[0]
                        ).format(Date(report.createdAt))
                    }

                    Card(
                        modifier = Modifier.fillMaxWidth(),

                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF2C2B24)
                        ),

                        shape = RoundedCornerShape(16.dp)
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {

                            AsyncImage(
                                model = File(report.imagePath),

                                contentDescription = null,

                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(190.dp)
                                    .clip(RoundedCornerShape(18.dp)),

                                contentScale = ContentScale.Crop
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),

                                horizontalArrangement =
                                    Arrangement.SpaceBetween,

                                verticalAlignment =
                                    Alignment.CenterVertically
                            ) {

                                Column(
                                    modifier = Modifier.weight(1f)
                                ) {

                                    Text(
                                        text = report.city,

                                        color = Color.White,

                                        fontSize = 22.sp,

                                        fontWeight = FontWeight.Bold
                                    )

                                    Spacer(modifier = Modifier.height(4.dp))

                                    Text(
                                        text = report.weatherCondition,

                                        color = Color.LightGray,

                                        fontSize = 14.sp
                                    )

                                    Spacer(modifier = Modifier.height(2.dp))

                                    Text(
                                        text = formattedDate,

                                        color = Color.Gray,

                                        fontSize = 12.sp
                                    )
                                }

                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(14.dp))
                                        .background(
                                            Color(0xFF5E6A00)
                                        )
                                        .padding(
                                            horizontal = 18.dp,
                                            vertical = 14.dp
                                        ),

                                    contentAlignment = Alignment.Center
                                ) {

                                    Text(
                                        text =
                                            "${report.temperature.toInt()}°C",

                                        color = Color.White,

                                        fontWeight = FontWeight.Bold,

                                        fontSize = 24.sp
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(18.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),

                                horizontalArrangement =
                                    Arrangement.spacedBy(12.dp)
                            ) {

                                Card(
                                    modifier = Modifier.weight(1f),

                                    colors = CardDefaults.cardColors(
                                        containerColor = Color(0xFF3A3323)
                                    ),

                                    shape = RoundedCornerShape(12.dp)
                                ) {

                                    Column(
                                        modifier = Modifier.padding(14.dp)
                                    ) {

                                        Text(
                                            text = "Original",

                                            color = Color.LightGray,

                                            fontSize = 12.sp
                                        )

                                        Spacer(modifier = Modifier.height(4.dp))

                                        Text(
                                            text =
                                                "${report.originalSizeKb} KB",

                                            color = Color(0xFFD08B28),

                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }

                                Card(
                                    modifier = Modifier.weight(1f),

                                    colors = CardDefaults.cardColors(
                                        containerColor = Color(0xFF233A33)
                                    ),

                                    shape = RoundedCornerShape(12.dp)
                                ) {

                                    Column(
                                        modifier = Modifier.padding(14.dp)
                                    ) {

                                        Text(
                                            text = "Compressed",

                                            color = Color.LightGray,

                                            fontSize = 12.sp
                                        )

                                        Spacer(modifier = Modifier.height(4.dp))

                                        Text(
                                            text =
                                                "${report.compressedSizeKb} KB",

                                            color = Color(0xFF4BAA94),

                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }

                            if (report.notes.isNotBlank()) {

                                Spacer(modifier = Modifier.height(14.dp))

                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(
                                            Color(0xFF4A4D38)
                                        )
                                        .padding(
                                            horizontal = 14.dp,
                                            vertical = 10.dp
                                        )
                                ) {

                                    Text(
                                        text = report.notes,

                                        color = Color.White,

                                        fontSize = 14.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
