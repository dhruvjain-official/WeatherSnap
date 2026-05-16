# WeatherSnap
WeatherSnap is an Android weather reporting app built using Jetpack Compose and MVVM architecture.
The app allows users to search live weather information, capture weather evidence using a custom CameraX camera, compress images, add field notes, and save reports locally using Room Database.

The project was developed as part of an Android Developer Internship assignment.


# Features
- Live weather search using Open-Meteo API
- Dynamic city suggestions while typing
- Weather loading and error states
- Weather condition mapping from weather codes
- Custom CameraX camera implementation
- Capture and preview images inside the app
- Image compression before saving reports
- Save weather reports locally using Room DB
- Report list with saved images and weather details
- Draft recovery for unsaved reports
- Local persistence using DataStore
- Smooth UI animations and transitions
- Material 3 dark themed UI



# Screenshots

Home Screen
<img width="720" height="1604" alt="Home Screen" src="https://github.com/user-attachments/assets/1485d26e-2cb9-4a66-a671-7501949aa84d" />

Weather Loaded
<img width="718" height="1599" alt="Weather Loaded" src="https://github.com/user-attachments/assets/fb8adb6f-e884-4c58-b446-1cbafe72d26f" />

Create Report Screen
<img width="718" height="1599" alt="Create Report Screen" src="https://github.com/user-attachments/assets/1bb08bbb-a811-4311-938b-b07898f3b1c3" />

Custom Camera
<img width="718" height="1599" alt="Custom Camera" src="https://github.com/user-attachments/assets/b294ef93-b333-4925-a7fa-b0249a58de98" />

Saved Reports Screen
<img width="718" height="1599" alt="Saved Reports" src="https://github.com/user-attachments/assets/95adb466-5a58-4429-976d-84de64295641" />


# Tech Stack
- Kotlin
- Jetpack Compose
- MVVM Architecture
- Hilt Dependency Injection
- Retrofit
- Gson Converter
- OkHttp Logging Interceptor
- Room Database
- CameraX
- Kotlin Coroutines
- Navigation Compose
- DataStore Preferences
- Coil Image Loading


# Architecture
The app follows MVVM architecture for proper separation of concerns.
UI Layer → ViewModel → Repository → API / Room Database
Main Layers
Presentation Layer
Compose UI Screens
ViewModels
UI State Handling
Data Layer
Repository classes
Retrofit API services
Room Database
DataStore draft management
Dependency Injection
Hilt modules for API and database dependencies


# Developer Judgment Challenge
The app handles unfinished report recovery using DataStore.
If the user:
searches weather
opens Create Report screen
captures a photo
writes notes
closes or kills the app before saving
the report draft can still be recovered later without creating duplicate saved reports.

The following draft data is restored:
Notes
Captured image
Selected city


# Image Compression
Captured images are compressed before saving reports locally.
The app displays:
Original image size
Compressed image size

This helps reduce local storage usage while keeping image previews available inside saved reports.


# API Used
Open-Meteo API
https://open-meteo.com/


# Project Structure
```
app/src/main/java/com/yourname/weathersnap
├── data
│   ├── local
│   │   ├── WeatherDatabase
│   │   ├── WeatherReportDao
│   │   └── WeatherReportEntity
│   │
│   ├── remote
│   │   ├── WeatherApi
│   │   ├── WeatherResponse
│   │   └── CityResponse
│   │
│   ├── WeatherRepository
│   └── ReportRepository
│
├── datastore
│   └── DraftManager
│
├── di
│   ├── DatabaseModule
│   └── NetworkModule
│
├── navigation
│   └── Routes
│
├── presentation
│   ├── weather
│   │   ├── WeatherScreen
│   │   └── WeatherViewModel
│   │
│   ├── report
│   │   ├── CreateReportScreen
│   │   ├── ReportsScreen
│   │   └── ReportViewModel
│   │
│   └── camera
│       └── CameraScreen
│
├── ui.theme
│
├── utils
│   └── ImageCompressor
│
├── MainActivity
└── WeatherSnapApp
```


# Setup Instructions

1. Clone the repository
```bash
git clone https://github.com/dhruvjain-official/WeatherSnap.git
```

3. Open the project in Android Studio

4. Sync Gradle

5. Run the application on an Android device or emulator


# Screen Recording
https://drive.google.com/file/d/19tAo0VyFpvmWvmiQNUUJnzT3FjPNDBiv/view?usp=sharing


# Author
Dhruv Jain
