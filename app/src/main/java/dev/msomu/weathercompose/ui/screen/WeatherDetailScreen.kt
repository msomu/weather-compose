package dev.msomu.weathercompose.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.msomu.weathercompose.data.DayWeather
import dev.msomu.weathercompose.data.getDummyData
import dev.msomu.weathercompose.ui.componet.HomeAppBar

@Composable
fun WeatherDetailScreen(cityName: String, date: String) {
    val weather = getDummyData().list.first()
    WeatherDetailContent(cityName, weather)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherDetailContent(cityName: String, weather: DayWeather, modifier: Modifier = Modifier) {
    Scaffold(
        topBar = { HomeAppBar(title = cityName) },
        content = { innerPadding ->
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)) {
                Text(
                    text = "${weather.temp.day}",
                    style = MaterialTheme.typography.displayLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(64.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Feels Like: ${weather.feels_like.day}",
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End
                )
                Spacer(modifier = Modifier.height(64.dp))
                Text(
                    text = weather.weather.first().main,
                    style = MaterialTheme.typography.displayMedium,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = weather.weather.first().description,
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start
                )
            }
        }
    )
}

@Preview
@Composable
fun WeatherDetailContentPreview() {
    WeatherDetailContent("Chennai",getDummyData().list.first())
}
