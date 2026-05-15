package com.yourname.weathersnap.di

import android.content.Context
import androidx.room.Room
import com.yourname.weathersnap.data.local.WeatherDatabase
import com.yourname.weathersnap.data.local.WeatherReportDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): WeatherDatabase {

        return Room.databaseBuilder(
            context,
            WeatherDatabase::class.java,
            "weather_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideWeatherReportDao(
        database: WeatherDatabase
    ): WeatherReportDao {

        return database.weatherReportDao()
    }
}