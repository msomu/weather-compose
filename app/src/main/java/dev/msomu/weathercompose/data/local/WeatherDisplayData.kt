package dev.msomu.weathercompose.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "weather_data",
)
data class WeatherDisplayData(
    @PrimaryKey
    val dt: Int,
    val cityName: String,
    val temp: Double,
    val feelsLike: Double,
    val weatherMain: String,
    val weatherDescription: String,
)
