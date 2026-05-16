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
import coil.compose.AsyncImage
import androidx.compose.ui.layout.ContentScale
import java.io.File
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import com.yourname.weathersnap.utils.ImageCompressor
import androidx.compose.ui.platform.LocalContext
import kotlin.math.roundToInt
import androidx.hilt.navigation.compose.hiltViewModel
import com.yourname.weathersnap.data.local.WeatherReportEntity
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import com.yourname.weathersnap.datastore.DraftManager
import kotlinx.coroutines.launch

@Composable
fun CreateReportScreen(
    viewModel: ReportViewModel = hiltViewModel(),
    navController: NavController,
    imagePath: String = ""
) {

    val weatherBackStackEntry = remember(
        navController.currentBackStackEntry
    ) {
        navController.getBackStackEntry("weather")
    }

    val savedStateHandle =
        weatherBackStackEntry.savedStateHandle

    var city by rememberSaveable {
        mutableStateOf(
            savedStateHandle
                .get<String>("city") ?: ""
        )
    }

    val temperature =
        savedStateHandle
            .get<Double>("temperature") ?: 0.0

    val humidity =
        savedStateHandle
            .get<Int>("humidity") ?: 0

    val wind =
        savedStateHandle
            .get<Double>("wind") ?: 0.0

    val pressure =
        savedStateHandle
            .get<Double>("pressure") ?: 0.0

    val weatherCode =
        savedStateHandle
            .get<Int>("weatherCode") ?: 0

    val weatherCondition = when (weatherCode) {

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


    val context = LocalContext.current

    val scope = rememberCoroutineScope()

    val draftManager = remember {
        DraftManager(context)
    }

    var restoredImagePath by rememberSaveable {
        mutableStateOf("")
    }

    var notes by rememberSaveable {
        mutableStateOf("")
    }

    LaunchedEffect(Unit) {

        val savedNotes =
            draftManager.getNotes()

        val savedImagePath =
            draftManager.getImagePath()

        val savedCity =
            draftManager.getCity()

        if (savedNotes.isNotEmpty()) {

            notes = savedNotes
        }

        if (savedImagePath.isNotEmpty()) {

            restoredImagePath =
                savedImagePath
        }

        if (savedCity.isNotEmpty()) {

            city = savedCity
        }
    }

    var isSaving by remember {
        mutableStateOf(false)
    }

    val finalImagePath =

        if (imagePath.isNotEmpty())
            imagePath

        else
            restoredImagePath

    val originalFile =
        if (finalImagePath.isNotEmpty())
            File(finalImagePath)
        else
            null

    LaunchedEffect(finalImagePath) {

        if (finalImagePath.isNotEmpty()) {

            draftManager.saveDraft(
                notes = notes,
                imagePath = finalImagePath,
                city = city
            )
        }
    }

    val compressedFile = remember(finalImagePath) {

        if (finalImagePath.isNotEmpty()) {

            ImageCompressor.compressImage(
                context,
                finalImagePath
            )

        } else {

            null
        }
    }

    val originalSizeKb =
        originalFile?.length()?.div(1024f)
            ?.roundToInt()

    val compressedSizeKb =
        compressedFile?.length()?.div(1024f)
            ?.roundToInt()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
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
                            text = "Capture, compress, annotate",

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

                        shape = RoundedCornerShape(20.dp)
                    ) {

                        Text(
                            text = "Back"
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),

            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF11150F)
            ),

            shape = RoundedCornerShape(10.dp)
        ) {

            Column(
                modifier = Modifier.padding(10.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),

                    horizontalArrangement = Arrangement.SpaceBetween,

                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column {

                        Text(
                            text = city,
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = weatherCondition,
                            color = Color.Gray,
                            fontSize = 12.sp
                        )
                    }

                    Text(
                        text =
                            "${temperature.toInt()}°C",
                        color = Color(0xFFB7E07A),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),

                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Card(
                        modifier = Modifier.weight(1f),

                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF24352B)
                        ),

                        shape = RoundedCornerShape(12.dp)
                    ) {

                        Column(
                            modifier = Modifier.padding(
                                horizontal = 8.dp,
                                vertical = 5.dp
                            ),

                            horizontalAlignment = Alignment.Start
                        ) {

                            Text(
                                text = "Humidity",
                                color = Color.Gray,
                                fontSize = 11.sp
                            )

                            Spacer(modifier = Modifier.height(1.dp))

                            Text(
                                text =
                                    "${humidity}%",
                                color = Color(0xFF5ED6B3),
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    Card(
                        modifier = Modifier.weight(1f),

                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF263545)
                        ),

                        shape = RoundedCornerShape(12.dp)
                    ) {

                        Column(
                            modifier = Modifier.padding(
                                horizontal = 8.dp,
                                vertical = 5.dp
                            ),

                            horizontalAlignment = Alignment.Start
                        ) {

                            Text(
                                text = "Wind",
                                color = Color.Gray,
                                fontSize = 11.sp
                            )

                            Spacer(modifier = Modifier.height(1.dp))

                            Text(
                                text = "${String.format("%.1f", wind / 3.6)} m/s",
                                color = Color(0xFF5DA9FF),
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    Card(
                        modifier = Modifier.weight(1f),

                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF3D3424)
                        ),

                        shape = RoundedCornerShape(12.dp)
                    ) {

                        Column(
                            modifier = Modifier.padding(
                                horizontal = 8.dp,
                                vertical = 5.dp
                            ),

                            horizontalAlignment = Alignment.Start
                        ) {

                            Text(
                                text = "Pressure",
                                color = Color.Gray,
                                fontSize = 11.sp
                            )

                            Spacer(modifier = Modifier.height(1.dp))

                            Text(
                                text =
                                    "${pressure}",
                                color = Color(0xFFFFB347),
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),

            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF2C2B24)
            ),

            shape = RoundedCornerShape(12.dp)
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0xFF4C4E3B),
                                    Color(0xFF5E6A00)
                                )
                            )
                        ),

                    contentAlignment = Alignment.Center
                ) {

                    if (finalImagePath.isNotEmpty()){

                        androidx.compose.animation.AnimatedVisibility(

                            visible = true,

                            enter =
                                fadeIn() + scaleIn()
                        ) {

                            AsyncImage(
                                model = File(finalImagePath),

                                contentDescription = null,

                                modifier = Modifier.fillMaxSize(),

                                contentScale = ContentScale.Crop
                            )
                        }

                    } else {

                        Text(
                            text = "Photo preview",
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Button(
                    onClick = {

                        if (finalImagePath.isNotEmpty()) {

                            File(finalImagePath).delete()

                            compressedFile?.delete()
                        }

                        navController.navigate("camera_screen")
                    },

                    modifier = Modifier.fillMaxWidth(),

                    shape = RoundedCornerShape(30.dp),

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFB9CB7A)
                    )
                ) {

                    Text(
                        text =
                            if (finalImagePath.isNotEmpty())
                                "Retake Photo"
                            else
                                "Capture Photo",
                        color = Color(0xFF2B2B1F)
                    )
                }
                if (finalImagePath.isNotEmpty()) {

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        Card(
                            modifier = Modifier.weight(1f),

                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFF3A3323)
                            ),

                            shape = RoundedCornerShape(10.dp)
                        ) {

                            Column(
                                modifier = Modifier.padding(12.dp)
                            ) {

                                Text(
                                    text = "Original",
                                    color = Color.LightGray,
                                    fontSize = 13.sp
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = "${originalSizeKb ?: 0} KB",
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

                            shape = RoundedCornerShape(10.dp)
                        ) {

                            Column(
                                modifier = Modifier.padding(12.dp)
                            ) {

                                Text(
                                    text = "Compressed",
                                    color = Color.LightGray,
                                    fontSize = 13.sp
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = "${compressedSizeKb ?: 0} KB",
                                    color = Color(0xFF4BAA94),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),

            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF11150F)
            ),

            shape = RoundedCornerShape(12.dp)
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = "Field Notes",
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(14.dp))

                OutlinedTextField(
                    value = notes,

                    onValueChange = {
                        notes = it

                        scope.launch {

                            draftManager.saveDraft(
                                notes = notes,
                                imagePath = finalImagePath,
                                city = city
                            )
                        }
                    },

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp),

                    placeholder = {
                        Text(
                            text = "Notes",
                            color = Color.Gray,
                            fontSize = 12.sp
                        )
                    },

                    shape = RoundedCornerShape(12.dp),

                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF8FA86E),
                        unfocusedBorderColor = Color(0xFF4A5440),

                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,

                        focusedContainerColor = Color(0xFF1B2118),
                        unfocusedContainerColor = Color(0xFF1B2118)
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Button(

            onClick = {
                if (isSaving) return@Button
                if (finalImagePath.isNotEmpty()) {

                    isSaving = true
                    viewModel.saveReport(

                        WeatherReportEntity(

                            city = city,

                            temperature =
                                temperature,

                            humidity =
                                humidity,

                            windSpeed =
                                wind,

                            pressure =
                                pressure,

                            weatherCondition =
                                weatherCondition,

                            notes = notes,

                            imagePath = finalImagePath,

                            compressedImagePath =
                                compressedFile?.absolutePath ?: "",

                            originalSizeKb =
                                originalSizeKb ?: 0,

                            compressedSizeKb =
                                compressedSizeKb ?: 0,

                            createdAt =
                                System.currentTimeMillis()
                        )
                    )
                    scope.launch {

                        draftManager.clearDraft()
                    }

                    navController.navigate("reports") {

                        popUpTo("weather")

                        launchSingleTop = true
                    }
                }
            },

            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(30.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFB9CB7A)
            )
        ) {

            Text(
                text = "Save Report",
                color = Color(0xFF2B2B1F)
            )
        }
    }
}