package dev.msomu.weathercompose.data.models

data class DailyForecast(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<DayWeather>,
    val message: Double
)