package dev.msomu.weathercompose.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.msomu.weathercompose.data.models.DayWeather
import dev.msomu.weathercompose.data.getDummyData
import dev.msomu.weathercompose.ui.componet.HomeAppBar

@Composable
fun WeatherListScreen(cityName: String, openWeatherDetail: (String, String) -> Unit) {
    val weatherList = getDummyData()
    WeatherListContent(weatherList.city.name, weatherList.list, onCLick = {
        openWeatherDetail(cityName, it)
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherListContent(cityName: String, weatherList: List<DayWeather>, onCLick: (String) -> Unit) {
    Scaffold(
        topBar = { HomeAppBar(title = cityName) },
        content = { innerPadding ->
            LazyColumn(
                contentPadding = innerPadding,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(count = weatherList.size) {
                    WeatherItem(weatherList[it], onCLick)
                }
            }
        }
    )
}

@Composable
fun WeatherItem(dayWeather: DayWeather, onCLick: (String) -> Unit, modifier: Modifier = Modifier) {
    Column(modifier.fillMaxWidth()) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(32.dp)
                .clickable { onCLick(dayWeather.dt.toString()) },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = dayWeather.weather.first().main,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )
            Text(
                text = "Temp: ${dayWeather.temp.day}",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )
        }
        Divider(
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
        )
    }
}

@Preview
@Composable
fun WeatherItemPreview() {
    WeatherItem(dayWeather = getDummyData().list.first(), {})
}