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
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.msomu.weathercompose.data.local.WeatherDisplayData
import dev.msomu.weathercompose.mock.getDummyData
import dev.msomu.weathercompose.ui.componet.HomeAppBar
import dev.msomu.weathercompose.util.Resource

@Composable
fun WeatherDetailScreen(
    cityName: String,
    detailViewState: State<Resource<WeatherDisplayData>>,
    date: String
) {
    val detailView = detailViewState.value.data
    WeatherDetailContent(cityName, detailView)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherDetailContent(cityName: String, weather: WeatherDisplayData?, modifier: Modifier = Modifier) {
    Scaffold(
        topBar = { HomeAppBar(title = cityName) },
        content = { innerPadding ->
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)) {
                Text(
                    text = "${weather?.temp}",
                    style = MaterialTheme.typography.displayLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(64.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Feels Like: ${weather?.feelsLike}",
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End
                )
                Spacer(modifier = Modifier.height(64.dp))
                Text(
                    text = "${weather?.weatherMain}",
                    style = MaterialTheme.typography.displayMedium,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "${weather?.weatherDescription}",
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
    WeatherDetailContent("Chennai", WeatherDisplayData(
        dt = 0,
        cityName = "Chennai",
        temp = 303.0,
        feelsLike = 304.0,
        weatherMain = "Clouds",
        weatherDescription = "overcast clouds"
    )
    )
}
