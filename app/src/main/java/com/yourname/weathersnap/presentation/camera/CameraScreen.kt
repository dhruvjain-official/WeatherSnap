package com.yourname.weathersnap.presentation.camera

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageCapture.OutputFileOptions
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import java.io.File

@Composable
fun CameraScreen(
    navController: NavController
) {

    val context = LocalContext.current

    var imageCapture by remember {
        mutableStateOf<ImageCapture?>(null)
    }

    var hasCameraPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == android.content.pm.PackageManager.PERMISSION_GRANTED
        )
    }

    val permissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { granted ->

            hasCameraPermission = granted
        }

    LaunchedEffect(Unit) {

        if (!hasCameraPermission) {

            permissionLauncher.launch(
                Manifest.permission.CAMERA
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        if (hasCameraPermission) {

            val lifecycleOwner = LocalLifecycleOwner.current

            AndroidView(
                factory = { ctx ->

                    val previewView = PreviewView(ctx)

                    val cameraProviderFuture =
                        ProcessCameraProvider.getInstance(ctx)

                    cameraProviderFuture.addListener({

                        val cameraProvider =
                            cameraProviderFuture.get()

                        val preview =
                            Preview.Builder().build()

                        val capture =
                            ImageCapture.Builder().build()

                        imageCapture = capture

                        preview.surfaceProvider =
                            previewView.surfaceProvider

                        val cameraSelector =
                            CameraSelector.DEFAULT_BACK_CAMERA

                        try {

                            cameraProvider.unbindAll()

                            cameraProvider.bindToLifecycle(
                                lifecycleOwner,
                                cameraSelector,
                                preview,
                                capture
                            )

                        } catch (e: Exception) {

                            e.printStackTrace()
                        }

                    }, ContextCompat.getMainExecutor(ctx))

                    previewView
                },

                modifier = Modifier.fillMaxSize()
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(
                    horizontal = 24.dp,
                    vertical = 26.dp
                ),

            horizontalArrangement = Arrangement.SpaceBetween,

            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Custom Camera",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            OutlinedButton(
                onClick = {
                    navController.popBackStack()
                },

                shape = RoundedCornerShape(20.dp),

                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.White
                ),

                border = ButtonDefaults.outlinedButtonBorder.copy(
                    brush = androidx.compose.ui.graphics.SolidColor(
                        Color.Gray
                    )
                )
            ) {

                Text("Close")
            }
        }

        Button(
            onClick = {

                val photoFile = File(
                    context.cacheDir,
                    "weather_${System.currentTimeMillis()}.jpg"
                )

                val outputOptions =
                    OutputFileOptions.Builder(photoFile).build()

                imageCapture?.takePicture(
                    outputOptions,

                    ContextCompat.getMainExecutor(context),

                    object : ImageCapture.OnImageSavedCallback {

                        override fun onImageSaved(
                            outputFileResults: ImageCapture.OutputFileResults
                        ) {

                            navController.navigate(
                                "create_report?imagePath=${photoFile.absolutePath}"
                            )
                        }

                        override fun onError(
                            exception: ImageCaptureException
                        ) {

                            exception.printStackTrace()
                        }
                    }
                )
            },


            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(
                    horizontal = 24.dp,
                    vertical = 34.dp
                )
                .fillMaxWidth(),

            shape = RoundedCornerShape(30.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFB9CB7A)
            ),



        ) {
            Text(
                text = "Capture",
                color = Color(0xFF2B2B1F)
            )
        }
    }
}