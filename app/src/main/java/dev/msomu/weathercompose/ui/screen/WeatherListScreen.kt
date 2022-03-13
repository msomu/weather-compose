package dev.msomu.weathercompose.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.insets.statusBarsHeight
import dev.msomu.weathercompose.data.DayWeather
import dev.msomu.weathercompose.data.getDummyData
import dev.msomu.weathercompose.ui.componet.HomeAppBar

@Composable
fun WeatherListScreen(cityName: String) {
    val weatherList = getDummyData()

}

@Composable
fun WeatherListContent(cityName: String, weatherList: List<DayWeather>) {

}