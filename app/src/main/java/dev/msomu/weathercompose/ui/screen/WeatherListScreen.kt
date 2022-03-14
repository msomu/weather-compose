package dev.msomu.weathercompose.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.msomu.weathercompose.data.local.WeatherDisplayData
import dev.msomu.weathercompose.ui.componet.HomeAppBar
import dev.msomu.weathercompose.util.Resource

@Composable
fun WeatherListScreen(
    cityName: String,
    weatherDisplayDataListState: State<Resource<List<WeatherDisplayData>>>,
    openWeatherDetail: (String, Int) -> Unit
) {
    when(weatherDisplayDataListState.value){
        is Resource.Loading -> {
            Column {
                CircularProgressIndicator()
                Text(text = "Loading")
            }
        }
        is Resource.Error -> {
            Text(text = "Error on Loading")
        }
        is Resource.Success -> {
            val weatherList = weatherDisplayDataListState.value.data ?: emptyList()
            WeatherListContent(cityName, weatherList) {
                openWeatherDetail(cityName, it)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherListContent(
    cityName: String,
    weatherList: List<WeatherDisplayData>,
    onCLick: (Int) -> Unit
) {
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
fun WeatherItem(
    dayWeather: WeatherDisplayData,
    onCLick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier.fillMaxWidth()) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(32.dp)
                .clickable { onCLick(dayWeather.dt) },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = dayWeather.weatherMain,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )
            Text(
                text = "Temp: ${dayWeather.temp}",
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
    WeatherItem(dayWeather = WeatherDisplayData(
        dt = 0,
        cityName = "Chennai",
        temp = 303.0,
        feelsLike = 307.0,
        weatherMain = "Clouds",
        weatherDescription = "More Clouds"
    ), {})
}