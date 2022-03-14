package dev.msomu.weathercompose.repository

import dev.msomu.weathercompose.data.local.WeatherDisplayData
import dev.msomu.weathercompose.util.Resource
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getWeather(cityName : String) : Flow<Resource<List<WeatherDisplayData>>>
    fun getWeather(cityName : String, dt : Int) : Flow<Resource<WeatherDisplayData>>
}