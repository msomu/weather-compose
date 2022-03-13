package dev.msomu.weathercompose.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.insets.statusBarsHeight

@Composable
fun HomeScreen(openWeatherList: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val appBarColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.87f)

        // Draw a scrim over the status bar which matches the app bar
        Spacer(
            Modifier
                .background(appBarColor)
                .fillMaxWidth()
                .statusBarsHeight()
        )
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                TextField(value = "", onValueChange = {})
                Button(onClick = { openWeatherList("chennai") }) {
                    Text(text = "Lookup")
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(openWeatherList = {})
}